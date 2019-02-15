package com.zzh.service;

import com.zzh.po.User;
import com.zzh.vo.RegisterUserVO;

import java.util.Map;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/9/29
 */

public interface UserService {

    User selectByPrimaryKey(int id);

    /**
     * 根据用户名查用户信息
     *
     * @param userName
     * @return
     */
    User selectByUserName(String userName);

    Map<String, String> getRules();

    /**
     * 注册用户
     * @param registerUserVO
     */
    int registerUser(RegisterUserVO registerUserVO);

    /**
     * 激活用户
     * @param activeCode
     */
    int updateUserStatus(String activeCode);
}
