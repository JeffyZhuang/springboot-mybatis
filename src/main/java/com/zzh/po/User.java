package com.zzh.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zzh
 * @date 2018-9-29
 */

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -7731026583659819909L;

    @ApiModelProperty(value = "用户的id")
    private Integer uid;
    @ApiModelProperty(value = "用户的名字")
    private String username;
    @ApiModelProperty(value = "用户的密码")
    private String password;
    @ApiModelProperty(value = "用户的邮箱")
    private String mail;
    @ApiModelProperty(value = "用户的状态 0未激活 1激活")
    private String activeStatus;
    @ApiModelProperty(value = "用户的激活码")
    private String activeCode;
}