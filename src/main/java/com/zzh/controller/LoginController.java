package com.zzh.controller;

import com.zzh.ErrorCode;
import com.zzh.config.ServerConfig;
import com.zzh.constant.Constant;
import com.zzh.dto.Mail;
import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.po.User;
import com.zzh.result.ApiResult;
import com.zzh.service.MailService;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserService;
import com.zzh.util.MD5Utils;
import com.zzh.vo.RegisterUserVO;
import com.zzh.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private MailService mailService;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 登陆接口
     *
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/login")
    public ApiResult login(@RequestBody UserVO userVO) {
        String userName = userVO.getUserName();
        String password = userVO.getPassword();
        User user = userService.selectByUserName(userName);
        if (user == null) {
            return ApiResult.fail(ErrorCode.USER_NO_EXIT.getMsg());
        } else {
            //判断是否激活
            if (user.getActiveStatus().equals(Constant.USER_ACTIVE_STATUS_0)) {
                return ApiResult.fail(ErrorCode.USER_NO_ACTIVE.getMsg());
            }
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
            return ApiResult.fail(ErrorCode.USER_VALID_ERROR.getMsg());
        }
    }


    /**
     * 注册接口
     *
     * @param registerUserVO
     * @return
     */
    @RequestMapping(value = "/register")
    public ApiResult register(@RequestBody RegisterUserVO registerUserVO) {
        String userName = registerUserVO.getUserName();
        int num = 0;
        User user = userService.selectByUserName(userName);
        if (user != null) {
            return new ApiResult(ErrorCode.USER_EXIT, "用户已存在");
        } else {
            //执行注册用户
            registerUserVO.setActiveCode(String.valueOf(UUID.randomUUID()));
            num = userService.registerUser(registerUserVO);
        }
        if (num > 0) {
            int roleNum = userRoleModuleService.addUserNormalRole(registerUserVO);
            if (roleNum > 0) {
                //发送激活邮件
                Mail mail = new Mail();
                mail.setEmail(registerUserVO.getEmail());
                mail.setSubject(Constant.ACTIVE_MAIL_SUBJECT);
                mail.setContent(Constant.ACTIVE_MAIL_CONTEXT + serverConfig.getUrl() + "/zzh/active?activeCode=" +
                        registerUserVO.getActiveCode());
                mailService.send(mail);
            }
            return ApiResult.success("注册成功");
        } else {
            return ApiResult.fail("注册失败");
        }
    }

    /**
     * 注册接口
     *
     * @param activeCode
     * @return
     */
    @RequestMapping(value = "/active")
    public ApiResult active(String activeCode) {
        int num = userService.updateUserStatus(activeCode);
        return ApiResult.success("激活成功");
    }
}
