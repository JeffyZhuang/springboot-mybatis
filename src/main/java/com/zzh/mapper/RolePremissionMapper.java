package com.zzh.mapper;

import com.zzh.dto.RoleRulesDTO;
import com.zzh.po.RolePremission;

import java.util.List;

public interface RolePremissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePremission record);

    int insertSelective(RolePremission record);

    RolePremission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePremission record);

    int updateByPrimaryKey(RolePremission record);

    List<RoleRulesDTO> getRoleRules();
}