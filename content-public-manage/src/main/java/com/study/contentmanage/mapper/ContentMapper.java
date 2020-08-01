package com.study.contentmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.contentmanage.bean.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentMapper extends BaseMapper<Content> {
}
