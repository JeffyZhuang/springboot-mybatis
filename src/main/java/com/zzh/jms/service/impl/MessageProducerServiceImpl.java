package com.zzh.jms.service.impl;

import com.zzh.jms.service.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;


/**
 * @Author: zzh
 * @Description:消息生产者
 * @Date: 2019/1/21
 */
@Service
public class MessageProducerServiceImpl implements MessageProducerService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void sendMessage(String msg) {
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}

