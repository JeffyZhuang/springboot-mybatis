package com.zzh.quarz.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author: zzh
 * @Description:quarz核心配置类
 * @Date: 2018/12/11
 */
@Configuration
public class ConfigureQuartz {
    private static final String QUARZ_PROPERTIES = "quarz.properties";

    //配置自定义的JobFactory
    @Bean
    public SpringJobFactory springJobFactory() {
        return new SpringJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(springJobFactory());
        //配置文件的注入
        schedulerFactoryBean.setConfigLocation(new ClassPathResource(QUARZ_PROPERTIES));
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }

}
