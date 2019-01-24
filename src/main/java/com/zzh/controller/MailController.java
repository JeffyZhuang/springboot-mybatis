package com.zzh.controller;

import com.zzh.result.ApiResult;
import com.zzh.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzh
 * @Description:邮件控制器
 * @Date: 2019/1/24
 */
@Slf4j
@RestController
@RequestMapping(value = "/zzh/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    /**
     * 通过邮箱激活用户
     *
     * @param mail 激活邮箱
     * @return
     */
    @RequestMapping(value = "/activeUser", method = RequestMethod.GET)
    public ApiResult startTask(@RequestParam(value = "mail") String mail){
        return ApiResult.success();
    }
}
