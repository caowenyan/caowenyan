package com.cao.util.hystrix;

import java.util.Map;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 配置dubbo分组才会拦截，否则所有分组都不会熔断降级
 * @date 2018年06月04日 10:35
 */
public class DubboHystrixProperty {
    /**
     * map中key是分组名，value是expression，按照aop的切面配置的规律，暂未实现
     * dubbo需要配置降级的分组
     */
    private Map<String,String> hystrixGroupNames;

    // 默认的降级方法
    private CommonFallback defaultFallback = new CommonFallback();

    // 扫描降级策略的包
    private String fallbackPackage;

    public Map<String, String> getHystrixGroupNames() {
        return hystrixGroupNames;
    }

    public void setHystrixGroupNames(Map<String, String> hystrixGroupNames) {
        this.hystrixGroupNames = hystrixGroupNames;
    }

    public CommonFallback getDefaultFallback() {
        return defaultFallback;
    }

    public void setDefaultFallback(CommonFallback defaultFallback) {
        this.defaultFallback = defaultFallback;
    }

    public String getFallbackPackage() {
        return fallbackPackage;
    }

    public void setFallbackPackage(String fallbackPackage) {
        this.fallbackPackage = fallbackPackage;
    }
}
