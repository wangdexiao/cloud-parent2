package com.study.base.mybatisplus.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoEntity {

    private String userName;
    private String userLog;
    private List<String> roles;
    private List<String> authories;
}
