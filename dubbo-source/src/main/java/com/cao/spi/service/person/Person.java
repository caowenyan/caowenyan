package com.cao.spi.service.person;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年04月23日 14:41
 */
@SPI
public interface Person {
    void say();
}
