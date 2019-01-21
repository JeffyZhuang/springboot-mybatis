package com.zzh.jms.service;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2019/1/21
 */
public interface MessageProducerService {
    /**
     * 生产消息
     * @param msg
     */
    void sendMessage(String msg) ;
}
