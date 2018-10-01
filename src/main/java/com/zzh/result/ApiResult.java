package com.zzh.result;


import com.zzh.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zzh
 * @Description:返回rest数据结构
 * @Date: 2018/9/29
 */
@Data
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 7783248559107241056L;
    /**
     * 返回结果编码
     */
    private int code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public ApiResult() {

    }

    public ApiResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResult(ErrorCode errorCode, Object data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public static ApiResult fail(Object data) {
        return new ApiResult(ErrorCode.FAIL, data);
    }

    public static ApiResult fail() {
        return new ApiResult(ErrorCode.FAIL, null);
    }

    public static ApiResult success(Object data) {
        return new ApiResult(ErrorCode.SUCCESS, data);
    }

    public static ApiResult success() {
        return new ApiResult(ErrorCode.SUCCESS, null);
    }

    public static ApiResult successWithSelfMsg(ErrorCode errorCode, String msg) {
        return new ApiResult(errorCode.getCode(), msg, null);
    }

    public static ApiResult failWithSelfMsg(ErrorCode errorCode, String msg) {
        return new ApiResult(errorCode.getCode(), msg, null);
    }


}
