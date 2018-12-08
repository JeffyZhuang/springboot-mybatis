package com.zzh.exception;

import lombok.Data;

/**
 * @Author: zzh
 * @Description: 自定义全局异常处理类
 * @Date: 2018/12/8
 */
@Data
public class GlobalException extends RuntimeException {
    private Integer code;

    public GlobalException(int code, String msg) {
        super(msg);
    }

    public GlobalException(){

    }


}
