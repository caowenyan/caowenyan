package com.cloudyoung.common.wx.hystrix.config;

import com.alibaba.dubbo.common.URL;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 命令相关配置
 * Created by yangshaokai on 2018/2/28.
 */
public class HystrixCommandPropertiesFactory {

    public static HystrixCommandProperties.Setter create(URL url, String method) {
        //降级的时间
        return HystrixCommandProperties.Setter().withCircuitBreakerSleepWindowInMilliseconds(url.getMethodParameter(method, "circuitBreaker.sleepWindowInMilliseconds", 5000))
                .withCircuitBreakerErrorThresholdPercentage(url.getMethodParameter(method, "circuitBreaker.errorThresholdPercentage", 50))
                .withCircuitBreakerRequestVolumeThreshold(url.getMethodParameter(method, "circuitBreaker.requestVolumeThreshold", 20))
                .withExecutionIsolationThreadInterruptOnTimeout(url.getMethodParameter(method, "execution.isolation.thread.interruptOnTimeout", true))
                .withExecutionTimeoutEnabled(url.getMethodParameter(method, "execution.timeout.enabled", false))//使用dubbo的超时，禁用这里的超时
//                .withExecutionTimeoutInMilliseconds(url.getMethodParameter(method, "execution.isolation.thread.timeoutInMilliseconds", 10000))
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(url.getMethodParameter(method, "fallback.isolation.semaphore.maxConcurrentRequests", 50))
                .withExecutionIsolationStrategy(IsolationStrategy.getIsolationStrategy(url))
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(url.getMethodParameter(method, "execution.isolation.semaphore.maxConcurrentRequests", 10))
                .withMetricsRollingStatisticalWindowInMilliseconds(url.getMethodParameter(method, "metrics.rollingStats.timeInMilliseconds", 10000));

    }
}
