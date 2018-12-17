package com.zzh.quarz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @Author: zzh
 * @Description:自定义jobFactory
 * @Date: 2018/12/11
 */
public class SpringJobFactory extends SpringBeanJobFactory {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance;
        try {
            jobInstance = autowireCapableBeanFactory.getBean(bundle.getJobDetail().getJobClass());
        } catch (Exception e) {
            jobInstance = autowireCapableBeanFactory.createBean(bundle.getJobDetail().getJobClass());
        }
        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}
