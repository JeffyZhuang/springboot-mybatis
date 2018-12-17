package com.zzh.enums;

/**
 * @Author: zzh
 * @Description:定时任务的枚举类
 * @Date: 2018/12/17
 */
public enum QuarzEnum {
    VALID(1, "有效"), NO_VALID(0, "失效");


    private int code;
    private String msg;

    QuarzEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg(){
        return msg;
    }


}
