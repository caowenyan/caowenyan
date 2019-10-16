package com.cloudyoung.common.wx.hystrix;

import com.cloudyoung.common.wx.hystrix.fallback.CommonFallback;

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
     */
    private static Map<String,String> hystrixGroupNames;//dubbo需要配置降级的分组

    private static CommonFallback defaultFallback = new CommonFallback();//默认的降级方法

    private static String FALLBACK_PACKAGE;//这里是一个常量，表示我们扫描降级策略的包

    public static Map<String, String> getHystrixGroupNames() {
        return hystrixGroupNames;
    }

    public static void setHystrixGroupNames(Map<String, String> hystrixGroupNames) {
        DubboHystrixProperty.hystrixGroupNames = hystrixGroupNames;
    }

    public static CommonFallback getDefaultFallback() {
        return defaultFallback;
    }

    public static void setDefaultFallback(CommonFallback defaultFallback) {
        DubboHystrixProperty.defaultFallback = defaultFallback;
    }

    public static String getFallbackPackage() {
        return FALLBACK_PACKAGE;
    }

    public static void setFallbackPackage(String fallbackPackage) {
        FALLBACK_PACKAGE = fallbackPackage;
    }
}
