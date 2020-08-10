package com.study.ssoserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.study.base.mybatisplus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wadexi
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色说明
     */
    private String remark;

}
