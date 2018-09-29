package com.zzh.service;

import com.zzh.po.User;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/9/29
 */

public interface UserService {

    User selectByPrimaryKey(int id);
}
