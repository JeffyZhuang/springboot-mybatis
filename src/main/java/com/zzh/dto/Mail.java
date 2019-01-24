package com.zzh.dto;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author: zzh
 * @Description:邮件实体类
 * @Date: 2019/1/24
 */
@Data
public class Mail {
    /**
     * 接收方邮箱
     */
    private String email;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 模板（选填）
     */
    private String template;

    /**
     * 其他自定义参数（选填）
     */
    private HashMap<String,String> kvMap;

}
