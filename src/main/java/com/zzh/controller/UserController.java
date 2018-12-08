package com.zzh.controller;

import com.zzh.ErrorCode;
import com.zzh.exception.GlobalException;
import com.zzh.exception.UnauthorizedException;
import com.zzh.po.User;
import com.zzh.result.ApiResult;
import com.zzh.service.UserService;
import com.zzh.shiro.JWTUtil;
import com.zzh.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zzh
 * @Description:用户API
 * @Date: 2018/9/29
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户", notes = "根据userId获取用户")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "query")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ApiResult getUserList(@RequestParam(value = "id") int id) {
        return ApiResult.success(userService.selectByPrimaryKey(id));

    }

    /**
     * 登陆接口
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult getUserList(@RequestBody UserVO userVO) {
        String userName = userVO.getUserName();
        String password = userVO.getPassword();
        User user = userService.selectByUserName(userName);
        if (user == null) {
            return new ApiResult(ErrorCode.USER_NO_EXIT, null);
        }
        if (user.getPassword().equals(password)) {
            return new ApiResult(ErrorCode.SUCCESS.getCode(), "Login SUCCESS", JWTUtil.sign(userName, password));
        } else {
            //把异常抛到自定义的异常类
            throw new UnauthorizedException();
        }
    }
}
