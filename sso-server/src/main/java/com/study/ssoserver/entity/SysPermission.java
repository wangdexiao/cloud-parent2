package com.study.ssoserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.study.base.mybatisplus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author wadexi
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_permission")
public class SysPermission extends BaseEntity {


    /**
     * 父权限 ID (0为顶级菜单)
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 授权标识符
     */
    private String code;

    /**
     * 授权路径
     */
    private String url;

    /**
     * 类型(1菜单，2按钮)
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;



}
