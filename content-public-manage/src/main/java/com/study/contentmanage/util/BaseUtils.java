package com.study.contentmanage.util;

import javax.servlet.http.HttpServletRequest;

public class BaseUtils {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
