package com.zzh.po;
import lombok.Data;

/**
 * @author zzh
 * @date 2018-9-29
 */

@Data
public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String phone;
}