package com.zzh.controller;

import com.zzh.ErrorCode;
import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.exception.UnauthorizedException;
import com.zzh.po.User;
import com.zzh.result.ApiResult;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserService;
import com.zzh.util.MD5Utils;
import com.zzh.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zzh
 * @Description:登录登出相关接口
 * @Date: 2018/12/26
 */
@RequestMapping("/zzh")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleModuleService userRoleModuleService;

    /**
     * 登陆接口
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/login")
    public ApiResult getUserList(@RequestBody UserVO userVO) {
        String userName = userVO.getUserName();
        String password = userVO.getPassword();
        User user = userService.selectByUserName(userName);
        if (user == null) {
            return new ApiResult(ErrorCode.USER_NO_EXIT, null);
        }
        if (user.getPassword().equals(password)) {
            UserRoleModuleDTO userRoleModuleDTO = userRoleModuleService.selectRolesModulesByUid(user.getUid());
            SecurityUtils.getSubject().login(new UsernamePasswordToken(userName, String.valueOf(MD5Utils.md5(user
                    .getPassword()))));
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("Authorization", SecurityUtils.getSubject().getSession().getId());
            resultMap.put("roles", userRoleModuleDTO.getRnameSet());
            return ApiResult.success(resultMap);
        } else {
            //把异常抛到自定义的异常类
            throw new UnauthorizedException();
        }
    }

}
