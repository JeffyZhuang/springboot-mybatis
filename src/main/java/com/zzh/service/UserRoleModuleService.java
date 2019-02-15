package com.zzh.service;

import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.vo.RegisterUserVO;

/**
 * @Author: zzh
 * @Description:用户角色资源service
 * @Date: 2018/11/13
 */
public interface UserRoleModuleService {

    /**
     * 根据用户的id查询角色和资源
     *
     * @param uid
     * @return
     */
    UserRoleModuleDTO selectRolesModulesByUid(int uid);

    /**
     * 插入用户的普通角色
     *
     * @param registerUserVO
     * @return
     */
    int addUserNormalRole(RegisterUserVO registerUserVO);
}
