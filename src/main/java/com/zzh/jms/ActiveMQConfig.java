package com.zzh.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @Author: zzh
 * @Description:消息配置类
 * @Date: 2019/1/21
 */
@Configuration
@EnableJms
public class ActiveMQConfig {
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("msg.queue");
    }
}
