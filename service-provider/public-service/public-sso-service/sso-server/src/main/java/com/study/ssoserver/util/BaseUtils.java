package com.study.ssoserver.util;

import javax.servlet.http.HttpServletRequest;

public class BaseUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-Wit");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
