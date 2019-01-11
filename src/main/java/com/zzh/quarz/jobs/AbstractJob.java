package com.zzh.quarz.jobs;

import com.zzh.RedisKeyPrefix;
import com.zzh.lock.DistributedRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zzh
 * @Description:Job抽象类，所有job实现都继承这个抽象类
 * @Date: 2019/1/11
 */
@Slf4j
@Service
public abstract class AbstractJob implements Job {
    @Autowired
    private DistributedRedisLock distributedRedisLock;

    private static final long TIME_OUT = 24 * 60 * 60 * 1000;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String key = "QuarzTaskJob:" + RedisKeyPrefix.QUARZ_TASK_PRE + this.getClass().getName();
        long startTime = System.currentTimeMillis();
        try {
            if (distributedRedisLock.lock(key, TIME_OUT)) {
                log.info("begin execute job : {" + key + "}");
                doJob(jobExecutionContext);
                long endTime = System.currentTimeMillis();
                log.info("end execute job ：{" + key + "}" + "const: " + (endTime - startTime) / 1000 + "s");
            }
        } catch (Exception e) {
            log.error("execute job error : {" + key + "}");
            throw  e;
        } finally {
            distributedRedisLock.releaseLock(key);
        }
    }

    private void doJob(JobExecutionContext jobExecutionContext) {
    }
}
