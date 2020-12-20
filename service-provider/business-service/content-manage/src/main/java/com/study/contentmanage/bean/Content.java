package com.study.contentmanage.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_f_content")
public class Content {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String content;
    private Date createTime;
    private Date updateTime;
    private int del;
}
