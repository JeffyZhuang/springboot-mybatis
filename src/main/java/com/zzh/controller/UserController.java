package com.zzh.controller;

import com.zzh.po.User;
import com.zzh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zzh
 * @Description:用户API
 * @Date: 2018/9/29
 */
@RestController
@Slf4j
@RequestMapping("/springboot")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUserList(@RequestParam(value = "id") int id) {
        return userService.selectByPrimaryKey(id);

    }

    @RequestMapping(value = "/hizzh", method = RequestMethod.GET)
    public String sayHi() {
        return "你最俗";
    }
}
