package com.cloudyoung.common.wx.hystrix;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.cloudyoung.common.wx.hystrix.config.SetterFactory;
import com.netflix.hystrix.HystrixCommand;

/**
 * dubbo拦截器，需要配置spi扫描
 */
@Activate(group = Constants.CONSUMER)
public class HystrixFilter implements Filter {

    private DubboHystrixProperty dubboHystrixProperty;

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        URL url = invoker.getUrl();

        if(dubboHystrixProperty.getHystrixGroupNames().containsKey(url.getParameter("group"))
                || dubboHystrixProperty.getHystrixGroupNames().equals("*")) {
            String methodName = invocation.getMethodName();
            String interfaceName = invoker.getInterface().getName();
            //获取相关熔断配置
            HystrixCommand.Setter setter = SetterFactory.create(interfaceName, methodName, url);
            //获取降级方法
            String fallback = url.getMethodParameter(methodName, "fallback");
            DubboCommand command = new DubboCommand(setter, invoker, invocation, fallback);
            Result result = command.execute();
            return result;
        }
        return invoker.invoke(invocation);
    }

    public void setDubboHystrixProperty(DubboHystrixProperty dubboHystrixProperty) {
        this.dubboHystrixProperty = dubboHystrixProperty;
    }
}
