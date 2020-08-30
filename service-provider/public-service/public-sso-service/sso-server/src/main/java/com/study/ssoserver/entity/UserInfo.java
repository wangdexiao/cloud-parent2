package com.study.ssoserver.entity;

import com.study.base.mybatisplus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

/**
 * 用户信息
 */
@Data
//@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity implements UserDetails{


    private  String username;
    private String password;
    private  boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean credentialsNonExpired;
    private  boolean enabled;
    private  Set<GrantedAuthority> authorities;

    private String nickName;
    private String mobile;
    private List<Role> role;


}

