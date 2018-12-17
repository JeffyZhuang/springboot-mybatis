package com.zzh.mapper;

import com.zzh.quarz.dto.QuarzTaskDTO;

import java.util.List;

/**
 * @Author: zzh
 * @Description:定时任务表操作Mapper
 * @Date: 2018/12/11
 */
public interface QuarzTaskMapper {

    List<QuarzTaskDTO> selectAllValidTask();

    QuarzTaskDTO selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(QuarzTaskDTO quarzTaskDTO);
}
