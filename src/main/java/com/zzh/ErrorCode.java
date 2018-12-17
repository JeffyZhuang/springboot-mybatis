package com.zzh;

import lombok.Getter;

/**
 * @Author: zzh
 * @Description:常见状态码
 * @Date: 2018/9/29
 */
@Getter
public enum ErrorCode {

    //异常代码 100以内
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    USER_NO_EXIT(9001, "用户不存在"),
    SCHEDULER_ERROR(9002,"定时任务调度失败 ")
    ;


    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
