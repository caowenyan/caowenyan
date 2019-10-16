package com.cloudyoung.common.wx.hystrix.fallback;

import com.cloudyoung.common.wx.hystrix.fallback.annotation.HystrixMethodAnnotation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 默认降级服务,必须包含HystrixMethodAnnotation注解
 * @date 2018年05月31日 17:12
 */
public class CommonFallback {
    public static volatile Map<Class,Object> result = new HashMap<>();

    @HystrixMethodAnnotation
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