package com.zzh.mapper;

import com.zzh.dto.RoleModuleDTO;
import com.zzh.po.ModuleRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ModuleRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleRole record);

    int insertSelective(ModuleRole record);

    ModuleRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleRole record);

    int updateByPrimaryKey(ModuleRole record);

    List<RoleModuleDTO> selectModulesByRid(@Param(value = "ridSet") Set<Integer> ridSet);
}