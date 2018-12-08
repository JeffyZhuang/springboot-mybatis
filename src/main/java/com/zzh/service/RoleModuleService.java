package com.zzh.service;

import com.zzh.dto.UserRoleModuleDTO;

import java.util.Set;

/**
 * @Author: zzh
 * @Description: 角色资源service
 * @Date: 2018/11/13
 */
public interface RoleModuleService {

    /**
     * 根据角色id查询资源
     *
     * @param ridSet
     * @return
     */
    UserRoleModuleDTO selectModulesByRid(Set<Integer> ridSet);
}
