package com.cy.pj.common.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yuanzhibx
 * @Date 2020-06-22
 */
@Component
public class DefaultMapCache {

    /*
        使用 map 对象作为存储数据的容器(HashTable) ConcurrentHashMap 线程安全
     */

    private Map<Object, Object> cache = new ConcurrentHashMap<>();

    public void putObject(Object key, Object value) {
        cache.put(key, value);
    }

    public Object getObject(Object key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
    }

}
