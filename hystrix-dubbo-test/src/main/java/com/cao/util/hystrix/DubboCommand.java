package com.cao.util.hystrix;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.cao.util.hystrix.fallback.annotation.HystrixAnnotation;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 若是需要配置个性化的降级方法，请设置包路径FALLBACK_PACKAGE，加上默认包路径
 */
public class DubboCommand extends HystrixCommand<Result> {
    private static final Logger logger = LogManager.getLogger(DubboCommand.class);
    private static ClassLoader classLoader = DubboCommand.class.getClassLoader();

    static Map<String,Class>map = new HashMap();

    private Invoker<?> invoker;
    private Invocation invocation;
    private String fallbackName;
    private DubboHystrixProperty dubboHystrixProperty;

    public DubboCommand(Setter setter, Invoker<?> invoker, Invocation invocation, String fallbackName, DubboHystrixProperty dubboHystrixProperty) {
        super(setter);
        this.invoker = invoker;
        this.invocation = invocation;
        this.fallbackName = fallbackName;
        this.dubboHystrixProperty = dubboHystrixProperty;
        init();
    }

    @Override
    protected Result run() throws Exception {
        Result result = invoker.invoke(invocation);
//        LogUtil.info(logger, PlatformNameEnum.WX, "interface: "+invoker.getInterface().getName(), "DubboCommand_invoke", invocation.getMethodName());
        //如果远程调用异常，抛出异常执行降级逻辑
        if (result.hasException()) {
            throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.COMMAND_EXCEPTION, DubboCommand.class, result.getException().getMessage(), result.getException(), null);
        }
        return result;
    }

    @Override
    protected Result getFallback() {
        try {
            if(StringUtils.isNotEmpty(fallbackName)) {
                Class clazz = map.get(invoker.getInterface().getName());
                if (clazz != null) {
                    return new RpcResult(clazz.getMethod(fallbackName).invoke(clazz.newInstance()));
                }
            }
            if(StringUtils.isEmpty(fallbackName) && dubboHystrixProperty.getDefaultFallback()!=null){
                Class returnType = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes()).getReturnType();
                return new RpcResult(dubboHystrixProperty.getDefaultFallback().invoke(returnType));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getFallback();
    }

    private void init() {
        File[] resources = getResources();//获取到包下所有的class文件
        for (int i = 0; i < resources.length; i++) {
            try {
                //载入包下的类
                Class<?> clazz = classLoader.loadClass(dubboHystrixProperty.getFallbackPackage() + "." + resources[i].getName().replace(".class", ""));
                HystrixAnnotation hystrixAnnotation = clazz.getAnnotation(HystrixAnnotation.class);
                if(hystrixAnnotation!=null){
                    map.put(hystrixAnnotation.interfaceClass().getName(),clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private File[] getResources() {
        try {
            File file = new File(classLoader.getResource(dubboHystrixProperty.getFallbackPackage().replace(".", "/")).toURI());
            return file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")) {//我们只扫描class文件
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到策略资源");
        }
    }

}
