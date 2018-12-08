package com.zzh.mapper;

import com.zzh.dto.UserRoleDTO;
import com.zzh.po.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRoleDTO> selectRolesByUid(int uid);
}