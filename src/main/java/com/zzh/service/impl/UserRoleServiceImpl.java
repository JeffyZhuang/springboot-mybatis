package com.zzh.service.impl;

import com.zzh.dto.UserRoleDTO;
import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.mapper.UserRoleMapper;
import com.zzh.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRoleModuleDTO selectRolesByUid(int uid) {
        List<UserRoleDTO> userRoleList = userRoleMapper.selectRolesByUid(uid);
        UserRoleModuleDTO userRoleModuleDTO = new UserRoleModuleDTO();
        userRoleModuleDTO.setUid(uid);
        Set<String> roleSet = new HashSet<>();
        Set<Integer> roleIdSet = new HashSet<>();
        for (UserRoleDTO userRoleDTO : userRoleList) {
            if (userRoleDTO.getUid() == uid) {
                roleSet.add(userRoleDTO.getRname());
                roleIdSet.add(userRoleDTO.getRid());
            }
        }
        userRoleModuleDTO.setRnameSet(roleSet);
        userRoleModuleDTO.setRidSet(roleIdSet);
        return userRoleModuleDTO;
    }
}
