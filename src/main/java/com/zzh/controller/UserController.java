package com.zzh.controller;

import com.zzh.ErrorCode;
import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.exception.UnauthorizedException;
import com.zzh.po.User;
import com.zzh.result.ApiResult;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserService;
import com.zzh.shiro.JWTUtil;
import com.zzh.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: zzh
 * @Description:用户API
 * @Date: 2018/9/29
 */
@RestController
@RequestMapping("/SpringBoot")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleModuleService userRoleModuleService;

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
     * 根据用户id获取用户的角色资源
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "/userRoleModule", method = RequestMethod.GET)
    public ApiResult userRoleModule(@RequestParam(value = "uid") int uid) {
        UserRoleModuleDTO userRoleModuleDTO = userRoleModuleService.selectRolesModulesByUid(uid);
        return ApiResult.success(userRoleModuleDTO);
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
            String sign = JWTUtil.sign(userName, password);
            return new ApiResult(ErrorCode.SUCCESS.getCode(), "Login SUCCESS", sign);
        } else {
            //把异常抛到自定义的异常类
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ApiResult article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ApiResult(200, "You are already logged in", null);
        } else {
            return new ApiResult(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ApiResult requireAuth() {
        return new ApiResult(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ApiResult requireRole() {
        return new ApiResult(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"add", "delete"})
    public ApiResult requirePermission() {
        return new ApiResult(200, "You are visiting permission require add,delete", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult unauthorized() {
        return new ApiResult(401, "Unauthorized", null);
    }
}
