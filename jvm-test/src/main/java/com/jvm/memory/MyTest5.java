package com.jvm.memory;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description 看泛型的字节码，这个泛型会自动增加一个T，开始一直迷糊
 * @date 2019年10月23日 16:15
 */
public class MyTest5<WTWWW> {
    WTWWW t;
    int sizeO = 0;
    int sizeT = 0;
    Object[] arrO;
    WTWWW[] arrT;

    public MyTest5(){
        arrO = (WTWWW[]) new Object[16];
        arrT = (WTWWW[]) new Object[16];
    }

    public MyTest5(WTWWW t) {
        this();
        this.t = t;
    }

    public MyTest5(WTWWW t, WTWWW[] arr) {
        this(t);
        this.arrO = arr;
        this.arrT = arr;
    }

    public WTWWW getO(int i) {
        return (WTWWW) arrO[i];
    }

    public void putO(WTWWW t) {
        arrO[sizeO++] = t;
    }

    public WTWWW getT(int i) {
        return arrT[i];
    }

    public void putT(WTWWW t) {
        arrT[sizeT++] = t;
    }

    public static void main(String[] args) {
        MyTest5<String> myTest5 = new MyTest5();
        myTest5.putO("发送O");
        myTest5.getO(0);
        myTest5.putO("ff发送O");
        String s = myTest5.getO(0);
        System.out.println(s);
        myTest5.putT("发送T");
        myTest5.getT(0);
        myTest5.putT("ff发送T");
        s = myTest5.getT(0);
        System.out.println(s);
    }
}
