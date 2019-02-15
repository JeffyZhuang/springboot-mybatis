package com.zzh.vo;

import lombok.Data;

/**
 * @Author: zzh
 * @Description:注册用户前端传参
 * @Date: 2019/1/25
 */
@Data
public class RegisterUserVO {
    private String userName;

    private String password;

    private String email;

    private String activeCode;
}
