package com.study.ssoserver.mapper;

import com.study.ssoserver.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

//    @Select("select user.username,user.password,user.fullname,user.mobile,role.role_name,\n" +
//            "\t\t\t role.description ,\n" +
//            "\t\t\t per.`code`,per.description\n" +
//            "\t\t from t_user user\n" +
//            "\n" +
//            "left join t_user_role on user.id = t_user_role.user_id\n" +
//            "left join t_role role on role.id = t_user_role.role_id\n" +
//            "LEFT JOIN t_role_permission role_per on role_per.role_id = role.id\n" +
//            "left join t_permission per on per.id = role_per.permission_id\n" +
//            "\n" +
//            "where username = 'admin'")
    UserInfo loadUserByUsername(@Param("userName") String username);
}
