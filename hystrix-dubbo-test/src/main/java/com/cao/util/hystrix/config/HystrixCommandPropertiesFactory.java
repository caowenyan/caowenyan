package com.cao.util.hystrix.config;

import com.alibaba.dubbo.common.URL;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 这个地方主要是通过dubbo的参数url来设置不同的hystrix参数
 * 这里最好把默认值通过配置中心配置，而不要写死
 */
public class HystrixCommandPropertiesFactory {

    public static HystrixCommandProperties.Setter create(URL url, String method) {
        //降级的时间
        return HystrixCommandProperties.Setter()
                .withCircuitBreakerSleepWindowInMilliseconds(url.getMethodParameter(method, "circuitBreaker.sleepWindowInMilliseconds", 5000))
                .withCircuitBreakerErrorThresholdPercentage(url.getMethodParameter(method, "circuitBreaker.errorThresholdPercentage", 50))
                .withCircuitBreakerRequestVolumeThreshold(url.getMethodParameter(method, "circuitBreaker.requestVolumeThreshold", 20))
                .withExecutionIsolationThreadInterruptOnTimeout(url.getMethodParameter(method, "execution.isolation.thread.interruptOnTimeout", true))
                //使用dubbo的超时，禁用这里的超时
                .withExecutionTimeoutEnabled(url.getMethodParameter(method, "execution.timeout.enabled", false))
//                .withExecutionTimeoutInMilliseconds(url.getMethodParameter(method, "execution.isolation.thread.timeoutInMilliseconds", 10000))
                .withFallbackIsolationSemaphoreMaxConcurrentRequests(url.getMethodParameter(method, "fallback.isolation.semaphore.maxConcurrentRequests", 50))
                .withExecutionIsolationStrategy(IsolationStrategy.getIsolationStrategy(url))
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(url.getMethodParameter(method, "execution.isolation.semaphore.maxConcurrentRequests", 10))
                .withMetricsRollingStatisticalWindowInMilliseconds(url.getMethodParameter(method, "metrics.rollingStats.timeInMilliseconds", 10000));

    }
}
