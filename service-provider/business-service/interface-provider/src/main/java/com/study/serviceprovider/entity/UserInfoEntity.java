package com.study.serviceprovider.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class UserInfoEntity {

    private String userName;
    private String userLog;
    private List<String> roles;
    private List<String> authories;
}
