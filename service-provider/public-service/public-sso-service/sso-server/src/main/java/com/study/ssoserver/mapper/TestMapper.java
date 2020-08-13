package com.study.ssoserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {


    @Select("select count(*) from t_user ")
    public int selectCount();

}
