package com.cao.start;

import com.alibaba.dubbo.config.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年02月27日 11:41
 */
public class Provider {
    public static void main(String[] args) {
        // 服务实现
        XxxService xxxService = new XxxServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("xxx");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
//        registry.setAddress("localhost:2181");
        // dobbo换成了dev
        registry.setAddress("localhost:2181?group=dev");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12340);
        protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        ServiceConfig<XxxService> service = new ServiceConfig<XxxService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setProvider(null);
        service.setInterface(XxxService.class);
        service.setRef(xxxService);
        service.setVersion("1.0.0");
        service.setLoadbalance("leastactive");
//        service.setGroup("serviceDev");

        // 暴露及注册服务
        service.export();
        System.out.println("provider start");
        new Scanner(System.in).next();
    }
}
