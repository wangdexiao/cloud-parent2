package com.study.ssoserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.study.base.mybatisplus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wadexi
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，加密存储, admin/1234
     */
    private String password;

    /**
     * 帐户是否过期(1 未过期，0已过期)
     */
    @TableField("is_account_non_expired")
    private Integer isAccountNonExpired;

    /**
     * 帐户是否被锁定(1 未过期，0已过期)
     */
    @TableField("is_account_non_locked")
    private Integer isAccountNonLocked;

    /**
     * 密码是否过期(1 未过期，0已过期)
     */
    @TableField("is_credentials_non_expired")
    private Integer isCredentialsNonExpired;

    /**
     * 帐户是否可用(1 可用，0 删除用户)
     */
    @TableField("is_enabled")
    private Integer isEnabled;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 注册手机号
     */
    private String mobile;

    /**
     * 注册邮箱
     */
    private String email;



}
