package com.study.ssoserver.entity;

import lombok.Data;

import java.util.List;

/**
 * 角色
 */
@Data
public class Role {

    private String name;
    private String remark;

    private List<RolePermission> permissionList;
}
