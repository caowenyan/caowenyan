package com.cao.util.hystrix.fallback.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主要作用为为了加载熔断后执行的类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HystrixAnnotation {

    /**
     * 执行降级的类
     */
    Class interfaceClass() default Object.class;
}
