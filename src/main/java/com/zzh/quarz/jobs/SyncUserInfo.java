package com.zzh.quarz.jobs;

import com.zzh.service.UserService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/12/12
 */
public class SyncUserInfo extends AbstractJob {

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job start...");
    }


}
