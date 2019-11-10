package com.jvm.bytes.code.dynamic.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description cglib动态代理
 * @date 2019年11月09日 16:35
 */
public class CglibClient {
    /**
     * 总结一下动态代理的jdk版本和cglib版本的生成的类有何差别
     * 1. jdk动态代理生成的类继承了Proxy类，感觉应该是只为了使用真实对象，所以动态代理必须是接口，因为只能是单继承
     * 2. 若是代理类中方法中调用方法B，为什么没有用代理呢？
     *      若是jdk动态代理，则代理生成的类必须含有InvocationHandler的实现类属性，所以调用的时候调用的是InvocationHandler.invoke(...method)，这个时候执行的是InvocationHandler的实现类，调用的是实际对象的方法，实际对象中本来就指向实际对象的this
     *      若是cglib，则代理对象新生成的继承了真实对象，在代理对象中通过super来调用真实对象，而不是把真是代理的对象中的所有的内容挪过来
     * 3. 如何在一个方法中调用当前类的代理对象的方法？若是spring，则通过AopContext.currentProxy()来实现，或者通过注解的方式来用代理对象实现
     */
    public static void main(String[] args) {
        //设置将cglib生成的代理类字节码生成到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/caowenyan/work/github/caowenyan/com/cglib/");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
                System.out.println("Before:" + method);
                Object object = proxy.invokeSuper(o, arg);
                System.out.println("After:" + method);
                return object;
            }
        });
        Subject subjet = (Subject)enhancer.create();
        //执行 test 方法
        subjet.test();
        System.out.println("proxy class:" + subjet.getClass());

    }
}
