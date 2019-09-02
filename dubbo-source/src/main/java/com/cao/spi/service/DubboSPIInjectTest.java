package com.cao.spi.service;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.cao.spi.service.log.Log;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description dubbo spi
 * @date 2019年04月23日 14:22
 */
public class DubboSPIInjectTest {
    public static void main(String[] args) {
        ExtensionLoader<Log> loader = ExtensionLoader.getExtensionLoader(Log.class);
        Log log = loader.getExtension("logset");
        log.execute();
    }
}
