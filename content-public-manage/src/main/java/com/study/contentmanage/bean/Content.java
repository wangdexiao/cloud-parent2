package com.study.contentmanage.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_f_content")
public class Content {

    private int id;
    private String content;
    private Date createTime;
    private Date updateTime;
    private int del;
}
