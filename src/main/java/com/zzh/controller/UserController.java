package com.zzh.controller;

import com.zzh.po.User;
import com.zzh.result.ApiResult;
import com.zzh.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/springboot")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="获取用户", notes="根据userId获取用户")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long",paramType = "query")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ApiResult getUserList(@RequestParam(value = "id") int id) {
        return ApiResult.success(userService.selectByPrimaryKey(id));

    }
}
