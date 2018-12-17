package com.zzh.quarz.dto;

import lombok.Data;

/**
 * @Author: zzh
 * @Description: 定时任务实体类
 * @Date: 2018/12/11
 */
@Data
public class QuarzTaskDTO {
    private Integer id;

    private String taskName;

    private String taskGroup;

    private String taskClass;

    private String cron;

    private String description;

    private Integer status;

}
