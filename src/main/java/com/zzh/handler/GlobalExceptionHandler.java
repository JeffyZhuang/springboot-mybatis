package com.zzh.handler;

import com.zzh.exception.UnauthorizedException;
import com.zzh.result.ApiResult;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zzh
 * @Description: 定义全局处理异常入口
 * @Date: 2018/12/8
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //捕捉Shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ApiResult handle401(ShiroException e) {
        return new ApiResult(HttpStatus.UNAUTHORIZED.value(), "无权限访问", null);
    }

    //捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ApiResult handle401(UnauthorizedException e) {
        return new ApiResult(HttpStatus.UNAUTHORIZED.value(), "用户登录失败，请检查用户账号密码", null);
    }

    //捕捉其他异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult globalException(HttpServletRequest request, Throwable ex) {
        return new ApiResult(getStaus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStaus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
