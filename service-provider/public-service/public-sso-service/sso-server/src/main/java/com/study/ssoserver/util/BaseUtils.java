package com.study.ssoserver.util;

import javax.servlet.http.HttpServletRequest;

public class BaseUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("x-requested-with");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

    public static boolean acceptJson(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return accept != null && accept.contains("application/json");
    }
}
