package com.study.ssoserver.entity;

import lombok.Data;

import java.util.List;


/**
 * 用户信息
 */
@Data
public class UserInfo {


    private String username;
    private String nickName;
    private String password;
    private String mobile;
    private List<Role> role;
    private List<RolePermission> permissions;

}
