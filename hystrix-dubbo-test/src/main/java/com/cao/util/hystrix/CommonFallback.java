package com.cao.util.hystrix;

import com.cao.util.hystrix.fallback.annotation.HystrixMethodAnnotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *  默认降级服务,必须包含HystrixMethodAnnotation注解
 */
public class CommonFallback {
    /**
     * key：需要降级的类
     * value：对key所对应的类进行降级
     */
    public static volatile Map<Class,Object> result = new HashMap();

    public void fallbackVoid(){}

    public Object invoke(Class returnType) throws Exception{
        if(result.size()==0){
            synchronized (result){
                if(result.size()==0){
                    Method[]methods = this.getClass().getMethods();
                    for (Method method:methods) {
                        if(null != method.getDeclaredAnnotation(HystrixMethodAnnotation.class)){
                            result.put(method.getReturnType(), method.invoke(this));
                        }
                    }
                }
            }
        }
        return result.get(returnType);
    }
}