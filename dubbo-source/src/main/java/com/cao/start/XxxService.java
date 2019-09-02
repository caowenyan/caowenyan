package com.cao.start;

import com.google.common.base.Objects;

/**
 * @author 曹文艳   (caowy@cloud-young.com)
 * @version V1.0
 * @description
 * @date 2019年02月27日 11:42
 */
public interface XxxService {
    Object get(String key);
    Object get(String key, int i);
    void set(String key, Object o);
    void put(String key, Object o);
}
