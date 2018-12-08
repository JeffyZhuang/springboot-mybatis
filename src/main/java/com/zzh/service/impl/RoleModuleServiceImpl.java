package com.zzh.service.impl;

import com.zzh.dto.RoleModuleDTO;
import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.mapper.ModuleRoleMapper;
import com.zzh.service.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/12/8
 */
@Service
public class RoleModuleServiceImpl implements RoleModuleService {

    @Autowired
    private ModuleRoleMapper moduleRoleMapper;

    @Override
    public UserRoleModuleDTO selectModulesByRid(Set<Integer> ridSet) {
        List<RoleModuleDTO> roleModuleDTOS = moduleRoleMapper.selectModulesByRid(ridSet);
        UserRoleModuleDTO userRoleModuleDTO = new UserRoleModuleDTO();
        Set<Integer> midSet = new HashSet<>();
        Set<String> mnameSet = new HashSet<>();
        for (RoleModuleDTO roleModuleDTO : roleModuleDTOS) {
            midSet.add(roleModuleDTO.getMid());
            mnameSet.add(roleModuleDTO.getMname());
        }
        userRoleModuleDTO.setMidSet(midSet);
        userRoleModuleDTO.setMnameSet(mnameSet);
        return userRoleModuleDTO;
    }
}
