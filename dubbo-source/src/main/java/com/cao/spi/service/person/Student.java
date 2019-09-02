package com.cao.spi.service.person;

import com.alibaba.dubbo.common.extension.Adaptive;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年04月23日 14:41
 */
@Adaptive
public class Student implements Person {
    public void say() {
        System.out.println("i'm a student");
    }
}
