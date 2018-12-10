package com.zzh.service.impl;

import com.zzh.mapper.UserMapper;
import com.zzh.po.User;
import com.zzh.redis.RedisHandle;
import com.zzh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/9/29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisHandle redisHandle;

    @Override
    public User selectByPrimaryKey(int id) {
        User user = (User) redisHandle.get(id);
        if (user == null) {
            log.info("-------------调数据库-------------");
            user = userMapper.selectByPrimaryKey(id);
            redisHandle.set(id, user);
        }
        return user;
    }

    @Override
    public User selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }
}
