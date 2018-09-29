package com.zzh.service.impl;

import com.zzh.mapper.UserMapper;
import com.zzh.po.User;
import com.zzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/9/29
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey() {
        return userMapper.selectByPrimaryKey(1);
    }
}
