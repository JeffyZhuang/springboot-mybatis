package com.zzh.controller;

import com.zzh.quarz.service.QuarzTaskService;
import com.zzh.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzh
 * @Description:定时任务接口
 * @Date: 2018/12/11
 */
@RestController
@RequestMapping(value = "/zzh/quarz")
@Slf4j
public class QuarzTaskController {

    @Autowired
    private QuarzTaskService quarzTaskService;

    /**
     * 启动id的定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/startTask", method = RequestMethod.GET)
    public ApiResult startTask(@RequestParam(value = "id") int id){
        int num = quarzTaskService.StartScheduleTask(id);
        return ApiResult.success(num);
    }
}
