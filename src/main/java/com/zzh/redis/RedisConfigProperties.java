package com.zzh.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zzh
 * @Description:redis配置类的实现
 * @Date: 2018/12/10
 */
@Configuration
@PropertySource(value = "classpath:redis.properties")
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfigProperties {

    private String host;

    private int port;

    private String password;

    private int database;

    private int maxActive;

    private int maxWait;

    private int maxIdle;

    private int minIdle;

    private int timeOut;
}
