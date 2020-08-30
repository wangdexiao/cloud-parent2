package com.study.contentmanage.security;


import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Map;

public interface PrincipalConverter {
    /**
     * 默认principal 只包含用户名
     * @param map
     * @return
     */
    default Object converter(Map<String, ?> map){
        Object principal = map.get(UserAuthenticationConverter.USERNAME);
        return principal;
    }
}
