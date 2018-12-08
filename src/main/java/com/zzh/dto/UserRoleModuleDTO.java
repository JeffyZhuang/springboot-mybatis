package com.zzh.dto;

import lombok.Data;

import java.util.Set;

/**
 * @Author: zzh
 * @Description:封装用户角色DTO
 * @Date: 2018/12/8
 */
@Data
public class UserRoleModuleDTO {
    private int uid;

    private Set<Integer> ridSet;

    private Set<String> rnameSet;

    private Set<Integer> midSet;

    private Set<String> mnameSet;

}
