package com.zzh.exception;

/**
 * @Author: zzh
 * @Description:定时任务失败异常
 * @Date: 2018/12/12
 */
public class SchedulerErrorException extends GlobalException {
    public SchedulerErrorException(int code, String msg) {
        super(code, msg);
    }

    public SchedulerErrorException() {
    }
}
