package com.zzh.service.impl;

import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.po.User;
import com.zzh.service.RoleModuleService;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserRoleService;
import com.zzh.service.UserService;
import com.zzh.vo.RegisterUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/11/13
 */
@Service
public class UserRoleModuleServiceImpl implements UserRoleModuleService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleModuleService roleModuleService;

    @Autowired
    private UserService userService;

    @Override
    public UserRoleModuleDTO selectRolesModulesByUid(int uid) {
        UserRoleModuleDTO userRoleModuleDTO = userRoleService.selectRolesByUid(uid);
        UserRoleModuleDTO result = roleModuleService.selectModulesByRid(userRoleModuleDTO.getRidSet());
        result.setUid(uid);
        result.setRidSet(userRoleModuleDTO.getRidSet());
        result.setRnameSet(userRoleModuleDTO.getRnameSet());
        return result;
    }

    @Override
    public int addUserNormalRole(RegisterUserVO registerUserVO) {
        User user =userService.selectByUserName(registerUserVO.getUserName());
        return userRoleService.insertUserRole(user.getUid());
    }
}
