package com.zzh.po;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzh
 * @date 2018-9-29
 */

@Data
public class User {
    @ApiModelProperty(value = "用户的id")
    private Integer userId;
    @ApiModelProperty(value = "用户的名字")
    private String userName;
    @ApiModelProperty(value = "用户的密码")
    private String password;
    @ApiModelProperty(value = "用户的电话")
    private String phone;
}