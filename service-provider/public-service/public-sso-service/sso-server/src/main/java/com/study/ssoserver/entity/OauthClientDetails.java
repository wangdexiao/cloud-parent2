package com.study.ssoserver.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.study.base.mybatisplus.entity.BaseEntity;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author wadexi
 * @since 2020-07-06
 */
@Data
@TableName("oauth_client_details")
public class OauthClientDetails extends BaseEntity {

//    private static final long serialVersionUID = 1L;

//    @TableId(value = "id")
//    private long id;
//    @TableField(value = "create_time",fill = FieldFill.INSERT)
//    private LocalDateTime createTime;
//    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
//    private LocalDateTime updateTime;
//    @TableField(value = "del")
//    private int del;


    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private boolean autoapprove;

//    private LocalDateTime createTime;
//
//    private LocalDateTime updateTime;
//
//    private int del;


}
