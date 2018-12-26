package com.zzh.controller;

import com.zzh.result.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: zzh
 * @Description:错误入口类
 * @Date: 2018/12/26
 */
public class ErrorController {

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult unauthorized() {
        return new ApiResult(401, "Unauthorized", null);
    }
}
