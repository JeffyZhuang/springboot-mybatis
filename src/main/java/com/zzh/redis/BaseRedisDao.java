package com.zzh.redis;

import java.util.Map;

/**
 * @Author: zzh
 * @Description:redis工具类接口
 * @Date: 2018/12/10
 */
public interface BaseRedisDao<K, V> {

    /**
     * 获取String类型的KV map
     *
     * @param key
     * @return
     */
    Map<String, String> getStringMap(String key);

    /**
     * 设置map的缓存
     *
     * @param key
     * @param value
     */
    void setMap(Object key, Map<String, String> value);

    /**
     * 设置obj对象
     *
     * @param key
     * @param value
     */
    void setObject(Object key, Object value);

    /**
     * 获取obj对象
     *
     * @param key
     * @return
     */
    Object getObject(Object key);
}
