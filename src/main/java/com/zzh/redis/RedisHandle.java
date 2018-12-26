package com.zzh.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/12/10
 */
@Slf4j
@Repository("redisHandle")
public class RedisHandle implements BaseRedisDao<String, Object> {
    @Resource(name = "redisTemplate")
    protected RedisTemplate redisTemplate;


    @Override
    public Map<String, String> getStringMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void setMap(Object key, Map<String, String> value) {
         redisTemplate.opsForHash().putAll(key,value);
    }

    @Override
    public void setObject(Object key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public Object getObject(Object key) {
        return redisTemplate.opsForValue().get(key);
    }
}