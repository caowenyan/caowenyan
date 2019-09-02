package com.cao.start;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年02月27日 11:42
 */
public class XxxServiceImpl implements XxxService {
    Map map = new HashMap();
    @Override
    public Object get(String key) {
        return map.get(key);
    }

    public Object get(String key, int i) {
        return map.get(key).toString() + i;
    }

    @Override
    public void set(String key, Object o) {
        System.out.println("set ...");
        map.put(key, o);
    }

    public void put(String key, Object o) {
        map.put(key, o);
    }
}
