package com.cao.util.hystrix.config;

import com.alibaba.dubbo.common.URL;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 隔离策略
 */
public class IsolationStrategy {

    /**
     * 线程池隔离
     */
    public static final String THREAD = "THREAD";
    /**
     * 信号量隔离
     */
    public static final String SEMAPHORE = "SEMAPHORE";

    /**
     * 获取隔离策略，默认使用线程池
     *
     * @param url
     * @return
     */
    public static HystrixCommandProperties.ExecutionIsolationStrategy getIsolationStrategy(URL url) {
        String isolation = url.getParameter("execution.isolation.strategy", THREAD);
        if (!isolation.equalsIgnoreCase(THREAD) && !isolation.equalsIgnoreCase(SEMAPHORE)) {
            isolation = THREAD;
        }
        if (isolation.equalsIgnoreCase(THREAD)) {
            return HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;
        } else {
            return HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;
        }
    }
}
