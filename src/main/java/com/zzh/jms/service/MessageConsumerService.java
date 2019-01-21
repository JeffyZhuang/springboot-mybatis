package com.zzh.jms.service;

/**
 * @Author: zzh
 * @Description:消息消费者
 * @Date: 2019/1/21
 */

public interface MessageConsumerService {

    /**
     * 接受消息
     * @param text
     */
    void receiveMessage(String text);
}
