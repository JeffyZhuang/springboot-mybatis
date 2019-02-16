package com.zzh.service.impl;

import com.zzh.RedisKeyPrefix;
import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.po.User;
import com.zzh.redis.RedisHandle;
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

    @Autowired
    private RedisHandle redisHandle;

    @Override
    public UserRoleModuleDTO selectRolesModulesByUid(int uid) {
        UserRoleModuleDTO result = (UserRoleModuleDTO) redisHandle.getObject(RedisKeyPrefix.USER_ROLE_MODULE + uid);
        if (result == null) {
            UserRoleModuleDTO userRoleModuleDTO = userRoleService.selectRolesByUid(uid);
            result = roleModuleService.selectModulesByRid(userRoleModuleDTO.getRidSet());
            result.setUid(uid);
            result.setRidSet(userRoleModuleDTO.getRidSet());
            result.setRnameSet(userRoleModuleDTO.getRnameSet());
            redisHandle.setObject(RedisKeyPrefix.USER_ROLE_MODULE + uid,result);
        }
        return result;
    }

    @Override
    public int addUserNormalRole(RegisterUserVO registerUserVO) {
        User user = userService.selectByUserName(registerUserVO.getUserName());
        return userRoleService.insertUserRole(user.getUid());
    }
}
