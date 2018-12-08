package com.zzh.service;

import com.zzh.dto.UserRoleModuleDTO;

/**
 * @Author: zzh
 * @Description:用户角色service
 * @Date: 2018/11/13
 */
public interface UserRoleService {

    /**
     * 根据用户的id查询角色
     *
     * @param uid
     * @return
     */
    UserRoleModuleDTO selectRolesByUid(int uid);
}
