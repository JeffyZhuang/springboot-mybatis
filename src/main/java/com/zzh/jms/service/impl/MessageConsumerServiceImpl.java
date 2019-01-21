package com.zzh.jms.service.impl;

import com.zzh.jms.service.MessageConsumerService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @Author: zzh
 * @Description:消息消费者service
 * @Date: 2019/1/21
 */
@Service
public class MessageConsumerServiceImpl implements MessageConsumerService {
    @Override
    @JmsListener(destination = "msg.queue")
    public void receiveMessage(String text) {
        System.err.println("【*** 接收消息 ***】" + text);
    }
}
