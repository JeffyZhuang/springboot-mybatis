package com.zzh.quarz;

import com.zzh.quarz.dto.QuarzTaskDTO;
import com.zzh.quarz.service.QuarzTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zzh
 * @Description:定时任务启动器，应用启动所有状态有效的定时任务启动
 * @Date: 2018/12/11
 */
@Slf4j
@Component
public class QuarzTaskStarter implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private QuarzTaskService quarzTaskService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            List<QuarzTaskDTO> quarzTaskDTOS = quarzTaskService.selectAllValidTask();
            for(QuarzTaskDTO quarzTaskDTO : quarzTaskDTOS){
                taskScheduler.scheduleTask(quarzTaskDTO);
            }
            taskScheduler.startSchedule();
            log.info("------------启动所有定时任务-----------");
        } catch (Exception e){
            log.error("启动所有定时任务异常");
        }
    }
}
