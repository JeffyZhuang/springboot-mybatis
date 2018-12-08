package com.zzh.service.impl;

import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.service.RoleModuleService;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserRoleService;
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

    @Override
    public UserRoleModuleDTO selectRolesModulesByUid(int uid) {
        UserRoleModuleDTO userRoleModuleDTO = userRoleService.selectRolesByUid(uid);
        UserRoleModuleDTO result = roleModuleService.selectModulesByRid(userRoleModuleDTO.getRidSet());
        result.setUid(uid);
        result.setRidSet(userRoleModuleDTO.getRidSet());
        result.setRnameSet(userRoleModuleDTO.getRnameSet());
        return result;
    }
}
