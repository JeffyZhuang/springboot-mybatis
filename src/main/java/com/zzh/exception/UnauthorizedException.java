package com.zzh.exception;

/**
 * @Author: zzh
 * @Description: 自定义无权限处理类
 * @Date: 2018/12/8
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {

    }

}
