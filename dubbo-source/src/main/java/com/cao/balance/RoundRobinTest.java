package com.cao.balance;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.AtomicPositiveInteger;
import com.alibaba.dubbo.rpc.Invoker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年05月10日 09:55
 */
public class RoundRobinTest {
    private static final ConcurrentMap<String, AtomicPositiveInteger> sequences = new ConcurrentHashMap<String, AtomicPositiveInteger>();

    private static final ConcurrentMap<String, AtomicPositiveInteger> indexSeqs = new ConcurrentHashMap<String, AtomicPositiveInteger>();

    static String key = "api.method";
    public static void main(String[] args) throws Exception {
        List<WeightC> list = new ArrayList<WeightC>();
        list.add(getWeight("A", 5));
        list.add(getWeight("B", 2));
        list.add(getWeight("C", 1));
        for( int i = 0 ; i < 8 ; i ++ ) {
            System.out.println(doSelect(list).toString());
        }
        System.out.println(indexSeqs.get(key).get());
    }
    protected static WeightC doSelect(List<WeightC> invokers) {
        int length = invokers.size();
        int maxWeight = 0;
        int minWeight = Integer.MAX_VALUE;
        final List<WeightC> invokerToWeightList = new ArrayList();

        // 查找最大和最小权重
        for (int i = 0; i < length; i++) {
            int weight = invokers.get(i).weight;
            maxWeight = Math.max(maxWeight, weight);
            minWeight = Math.min(minWeight, weight);
            if (weight > 0) {
                invokerToWeightList.add(invokers.get(i));
            }
        }

        // 获取当前服务对应的调用序列对象 AtomicPositiveInteger
        AtomicPositiveInteger sequence = sequences.get(key);
        if (sequence == null) {
            // 创建 AtomicPositiveInteger，默认值为0
            sequences.putIfAbsent(key, new AtomicPositiveInteger());
            sequence = sequences.get(key);
        }

        // 获取下标序列对象 AtomicPositiveInteger
        AtomicPositiveInteger indexSeq = indexSeqs.get(key);
        if (indexSeq == null) {
            // 创建 AtomicPositiveInteger，默认值为 -1
            indexSeqs.putIfAbsent(key, new AtomicPositiveInteger(-1));
            indexSeq = indexSeqs.get(key);
        }

        if (maxWeight > 0 && minWeight < maxWeight) {
            length = invokerToWeightList.size();
            while (true) {
                int index = indexSeq.incrementAndGet() % length;
                int currentWeight = sequence.get() % maxWeight;

                // 每循环一轮（index = 0），重新计算 currentWeight
                if (index == 0) {
                    currentWeight = sequence.incrementAndGet() % maxWeight;
                }

                // 检测 Invoker 的权重是否大于 currentWeight，大于则返回
                if (invokerToWeightList.get(index).weight > currentWeight) {
                    return invokerToWeightList.get(index);
                }
            }
        }

        // 所有 Invoker 权重相等，此时进行普通的轮询即可
        return invokers.get(sequence.incrementAndGet() % length);
    }
    public static WeightC getWeight (String name, int weight) {
        WeightC weightC = new WeightC();
        weightC.name = name;
        weightC.weight = weight;
        return weightC;
    }

    static class WeightC {
        String name;
        int weight;
        public WeightC(){};

        @Override
        public String toString() {
            return "WeightC [name: " +name+ ", weight : " + weight + "]";
        }
    }
}
