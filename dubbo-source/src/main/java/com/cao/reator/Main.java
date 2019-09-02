package com.cao.reator;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年05月09日 13:15
 */
public class Main {
    public static void main(String[] args) throws Exception{
        new Thread(new Reactor(8080)).start();
    }
}
