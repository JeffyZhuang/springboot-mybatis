package com.zzh.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: zzh
 * @Description: 用户token
 * @Date: 2018/12/8
 */
public class JWTToken implements AuthenticationToken {

    //密匙
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
