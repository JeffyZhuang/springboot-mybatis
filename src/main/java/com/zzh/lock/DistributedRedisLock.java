package com.zzh.lock;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: zzh
 * @Description:redis分布式锁
 * @Date: 2019/1/11
 * <p>
 * https://my.oschina.net/dengfuwei/blog/1600681
 */
@Slf4j
@Component
public class DistributedRedisLock {
    private static final String SET_IF_NOT_EXIT = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final String UNLOCK_LUA = "if redis.call(\"get\", KEYS[1]) == ARGV[1] " + "then " + "    return "
            + "redis.call(\"del\",KEYS[1]) " + "else " + "    return 0 " + "end ";

    @Autowired
    private RedisTemplate redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<>();

    /**
     * 加锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean lock(String key, long expire) {
        return lock(key, expire, 0, 0);
    }

    /**
     * 加锁
     *
     * @param key         锁的唯一标识
     * @param expire      过期时间
     * @param retryTimes  加锁失败后的重试次数
     * @param sleepMillis 每次重试的时间间隔
     * @return
     */
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setRedis(key, expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while ((!result) && retryTimes-- > 0) {
            try {
                log.debug("lock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }


    private boolean setRedis(String key, long expire) {
        try {
            String result = (String) redisTemplate.execute((RedisCallback<String>) connection -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                String uuid = UUID.randomUUID().toString();
                lockFlag.set(uuid);
                return commands.set(key, uuid, SET_IF_NOT_EXIT, SET_WITH_EXPIRE_TIME, expire);

            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("set redis occured an exception", e);
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param key
     * @return
     */
    public boolean releaseLock(String key) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = new ArrayList<String>();
            keys.add(key);
            List<String> args = new ArrayList<String>();
            args.add(lockFlag.get());

            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本

            Long result = (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }

                // 单机模式
                else if (nativeConnection instanceof RedisProperties.Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;

            });
            return result != null && result > 0;
        } catch (Exception e) {
            log.error("release lock occured an exception", e);
        }
        return false;
    }
}
