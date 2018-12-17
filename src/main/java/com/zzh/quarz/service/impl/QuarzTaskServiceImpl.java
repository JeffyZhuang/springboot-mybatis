package com.zzh.quarz.service.impl;

import com.zzh.enums.QuarzEnum;
import com.zzh.quarz.TaskScheduler;
import com.zzh.quarz.dto.QuarzTaskDTO;
import com.zzh.mapper.QuarzTaskMapper;
import com.zzh.quarz.service.QuarzTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zzh
 * @Description:定时任务service实现类
 * @Date: 2018/12/11
 */
@Service
public class QuarzTaskServiceImpl implements QuarzTaskService {

    @Autowired
    private QuarzTaskMapper quarzTaskMapper;

    @Autowired
    private TaskScheduler taskScheduler;

    @Override
    public List<QuarzTaskDTO> selectAllValidTask() {
        return quarzTaskMapper.selectAllValidTask();
    }

    @Override
    public int StartScheduleTask(int id) {
        QuarzTaskDTO quarzTaskDTO = quarzTaskMapper.selectByPrimaryKey(id);
        if (quarzTaskDTO.getStatus() == QuarzEnum.NO_VALID.getCode()) {
            quarzTaskDTO.setStatus(QuarzEnum.VALID.getCode());
            taskScheduler.scheduleTask(quarzTaskDTO);
            quarzTaskMapper.updateByPrimaryKeySelective(quarzTaskDTO);
        } else {
            taskScheduler.scheduleTask(quarzTaskDTO);
        }
        return 1;
    }
}
