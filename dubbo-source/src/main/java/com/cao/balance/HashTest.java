package com.cao.balance;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.Invoker;
import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.TreeMap;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年05月09日 18:20
 */
public class HashTest {

    private static final TreeMap<Long, Object> virtualInvokers = new TreeMap<Long, Object>();

    static List<String> list = Lists.asList("1","2", new String[0]);
    static Integer replicaNumber = 160;

    public static void main(String[] args) throws Exception {
        select();
        System.out.println(JSON.json(virtualInvokers));
    }

    private static void select() {
        for (String address : list) {
            for (int i = 0; i < replicaNumber / 4; i++) {
                byte[] digest = md5(address + i);
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    virtualInvokers.put(m, address);
                }
            }
        }
    }

    private static long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }

    private static byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes;
        try {
            bytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.update(bytes);
        return md5.digest();
    }
}
