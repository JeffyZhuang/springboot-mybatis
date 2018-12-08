package com.zzh.exception;

import lombok.Data;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/12/8
 */
@Data
public class UnauthorizedException extends GlobalException {

    public UnauthorizedException(int code, String msg) {
        super(code, msg);
    }

    public UnauthorizedException() {
    }


}
