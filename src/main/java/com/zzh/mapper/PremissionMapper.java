package com.zzh.mapper;

import com.zzh.po.Premission;

public interface PremissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Premission record);

    int insertSelective(Premission record);

    Premission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Premission record);

    int updateByPrimaryKey(Premission record);
}