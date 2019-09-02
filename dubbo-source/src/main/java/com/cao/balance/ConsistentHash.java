package com.cao.balance;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.google.common.collect.Lists;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConsistentHash<T> {

    private final int numberOfReplicas;
    private volatile TreeMap<Integer, List<T>> circle = new TreeMap();

    private static final int circleSize = 188833;

    public static void main(String[] args) throws Exception {
        List nodes = new ArrayList(Lists.asList("A","B",new String[0]));
        nodes.add("C");
        ConsistentHash hash = new ConsistentHash(10, nodes);
        System.out.println(JSON.json(hash.circle));
        hash.add("D");
        System.out.println(JSON.json(hash.circle));
        hash.remove("C");
        System.out.println(JSON.json(hash.circle));
    }

    /***
     * 
     * @param numberOfReplicas
     * 	每个节点的虚拟节点的个数
     * @param nodes
     */
    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for (T node : nodes) {
            addNode(circle, node);
        }
    }

    public synchronized void add(T node) {
        TreeMap<Integer, List<T>> newCircle = copyCircle();
        addNode(newCircle, node);
        this.circle = newCircle;
    }

    public synchronized void remove(T node)	{
        TreeMap<Integer, List<T>> newCircle = copyCircle();
        remove(newCircle, node);
        this.circle = newCircle;
    }

    private TreeMap<Integer, List<T>> copyCircle() {
        TreeMap<Integer, List<T>> newTree = new TreeMap();

        for (Map.Entry<Integer, List<T>> entry : circle.entrySet())	{
            List<T> list = new ArrayList<T>();
            list.addAll(entry.getValue());
            newTree.put(entry.getKey(), list);
        }
        return newTree;
    }

    private void addNode(TreeMap<Integer, List<T>> circle, T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int key = hashMd5(node.toString() + i);
            List<T> list = circle.get(key);
            if (list == null) {
                list = new ArrayList<T>();
                circle.put(key, list);
            }
            if (!containsNode(list, node)) {
                list.add(node);
            }
        }
    }

    private void removeNodeToList(List<T> list, T node)	{
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (node.equals(it.next())) {
                it.remove();
            }
        }
    }

    private boolean containsNode(List<T> list, T node) {
        for (T t : list) {
            if (t.equals(node))	{
                return true;
            }
        }
        return false;
    }
    
    private void remove(TreeMap<Integer, List<T>> circle, T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            int key = hashMd5(node.toString() + i);
            List<T> list = circle.get(key);
            if (list != null) {
                if (list.contains(node)) {
                    removeNodeToList(list, node);
                }
                if (list.isEmpty())	{
                    circle.remove(key);
                }
            }
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashMd5(key);
        Map.Entry<Integer, List<T>> entry = circle.ceilingEntry(hash);    //返回键值对，该键至少大于或等于给定键，如果不存在这样的键的键 - 值映射，则返回null相关联。
        List<T> node = null;
        if (entry == null) {
            node = circle.firstEntry().getValue();
        }
        else {
            node = entry.getValue();
        }
        if (node != null && !node.isEmpty()) {
            return node.get(0);
        }
        return null;
    }

    private static int hashCode(byte[] bytes) {
        int hash = 0;
        for (byte b : bytes) {
            hash = hash * 31 + ((int) b & 0xFF);
            if (hash > 0x4000000) {
                hash = hash % 0x4000000;
            }
        }
        return hash;
    }

    private  int hashMd5(Object o) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(o.toString().getBytes());
            int hashCode = hashCode(bytes);
            return hashCode % circleSize;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }
}