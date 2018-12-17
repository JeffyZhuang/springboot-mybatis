package com.zzh.quarz.service;

import com.zzh.quarz.dto.QuarzTaskDTO;

import java.util.List;

/**
 * @Author: zzh
 * @Description: 定时任务Service接口
 * @Date: 2018/12/11
 */
public interface QuarzTaskService {
    /**
     * 获取所有有效的定时任务
     *
     * @return
     */
    List<QuarzTaskDTO> selectAllValidTask();

    /**
     * 根据ID开启定时任务
     *
     * @return
     */
    int  StartScheduleTask(int id);
}
