package com.zzh.service;

import com.zzh.dto.Mail;

/**
 * @Author: zzh
 * @Description:邮件服务
 * @Date: 2019/1/24
 */
public interface MailService {

    /**
     * 发送邮件
     * @param mail
     */
    void send(Mail mail);
}
