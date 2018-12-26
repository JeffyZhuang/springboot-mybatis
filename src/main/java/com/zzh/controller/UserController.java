package com.zzh.controller;

import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.result.ApiResult;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserService;
import com.zzh.util.SessionUtils;
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
@RequestMapping("/zzh/user")
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
        String userName = SessionUtils.getCurrentUserName();
        System.out.println("userName = " + userName);
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

}
