package com.zzh.controller;

import com.zzh.po.User;
import com.zzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/9/29
 */
@RestController
@RequestMapping("/springboot")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserList() {
        return userService.selectByPrimaryKey();

    }

    @RequestMapping(value = "/hizzh",method = RequestMethod.GET)
    public String sayHi(){
        return "你最俗";
    }
}