package com.zzh.quarz;

import com.zzh.ErrorCode;
import com.zzh.exception.SchedulerErrorException;
import com.zzh.quarz.dto.QuarzTaskDTO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author: zzh
 * @Description:定时任务调度
 * @Date: 2018/12/11
 */
@Slf4j
@Component
public class TaskScheduler {

    @Autowired
    private Scheduler scheduler;

    public void scheduleTask(QuarzTaskDTO quarzTaskDTO) {
        try {
            Class<?> jobClass = Class.forName(quarzTaskDTO.getTaskClass());
            if (Job.class.isAssignableFrom(jobClass)) {
                JobDetail jobDetail = buildJobDetail(quarzTaskDTO, (Class<? extends Job>) jobClass);
                jobDetail.getJobDataMap().put("taskId", quarzTaskDTO.getId());
                Trigger trigger = buildTrigger(quarzTaskDTO);
                scheduler.scheduleJob(jobDetail, trigger);
                log.info("定时任务调度成功： " + quarzTaskDTO);
            }
        } catch (Exception e) {
            log.error("定时任务调度失败：" + quarzTaskDTO);
            throw new SchedulerErrorException(ErrorCode.SCHEDULER_ERROR.getCode(), ErrorCode.SCHEDULER_ERROR.getMsg()
                    + e.getMessage());
        }
    }

    private Trigger buildTrigger(QuarzTaskDTO quarzTaskDTO) {
        return TriggerBuilder.newTrigger().withIdentity(quarzTaskDTO.getTaskName(), quarzTaskDTO.getTaskGroup())
                .startNow().withSchedule(CronScheduleBuilder.cronSchedule(quarzTaskDTO.getCron())).build();
    }

    private JobDetail buildJobDetail(QuarzTaskDTO quarzTaskDTO, Class<? extends Job> clazz) {
        return JobBuilder.newJob(clazz).withIdentity(getTaskName(quarzTaskDTO), getTaskGroup(quarzTaskDTO)).build();
    }

    private String getTaskGroup(QuarzTaskDTO quarzTaskDTO) {
        return StringUtils.isEmpty(quarzTaskDTO.getTaskGroup()) ? Scheduler.DEFAULT_GROUP : quarzTaskDTO.getTaskGroup();
    }

    private String getTaskName(QuarzTaskDTO quarzTaskDTO) {
        return StringUtils.isEmpty(quarzTaskDTO.getTaskName()) ? quarzTaskDTO.getTaskClass() : quarzTaskDTO
                .getTaskName();
    }


    public void startSchedule() {
        try {
            if (scheduler.isShutdown()) {
                scheduler.start();
                log.info("启动定时任务成功");
            }
        } catch (SchedulerException e) {
            log.error("启动定时任务失败 ：" + e);
        }
    }
}
