package com.study.base.mybatisplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/**
 * 用户信息
 */
@Data
@AllArgsConstructor
public class UserInfoEntity  {



//    private  String account;
    private  String username;
    private String name;
    private List<Role> role;
    private List<String> authorities;
    private String mobile;

    private List<String> aud;
    private List<String> scope;
//    private Date exp;
    private Long exp;
    private String jti;
    private String clientId;


    public UserInfoEntity(){
    }

    public UserInfoEntity(String username) {
        this.username = username;
    }
}


