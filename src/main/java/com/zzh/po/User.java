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
}