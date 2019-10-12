package com.jvm.bytes.code.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年10月12日 10:17
 */
public class DynamicSubjectHandler implements InvocationHandler {
    private Object subject;

    public DynamicSubjectHandler(Object obj) {
        this.subject = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method: " + method.getName());
        // 这个地方注意是传入的参数，而不是proxy，因为proxy同样已经代理过了，一直重复执行，出现栈溢出
        Object result = method.invoke(subject, args);
        System.out.println("after method: " + method.getName());
        return result;
    }
}
