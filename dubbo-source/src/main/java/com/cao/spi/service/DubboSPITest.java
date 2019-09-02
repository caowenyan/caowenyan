package com.cao.spi.service;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import com.cao.spi.service.log.Log;
import com.cao.spi.service.person.Person;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年04月23日 14:22
 */
public class DubboSPITest {
    public static void main(String[] args) {
//        ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
//        loader.getAdaptiveExtension();

//        ExtensionLoader<Log> loader = ExtensionLoader.getExtensionLoader(Log.class);
//        Log log = loader.getExtension("logback");
//        log.execute();

        ExtensionLoader<Person> loader = ExtensionLoader.getExtensionLoader(Person.class);
        Person person = loader.getExtension("teacher");
        person.say();
    }
}
