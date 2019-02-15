package com.zzh.mapper;

import com.zzh.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author zzh
 * @date 2018-9-29
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String userName);

    int updateUserStatus(@Param("activeCode") String activeCode);
}