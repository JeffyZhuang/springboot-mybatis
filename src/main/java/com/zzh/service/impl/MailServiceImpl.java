package com.zzh.service.impl;

import com.zzh.dto.Mail;
import com.zzh.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2019/1/24
 */
@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    public void send(Mail mail) {
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        mainMessage.setFrom(environment.getProperty("spring.mail.username"));
        mainMessage.setTo(mail.getEmail());
        mainMessage.setSubject(mail.getSubject());
        mainMessage.setText(mail.getContent());
        javaMailSender.send(mainMessage);
    }
}
