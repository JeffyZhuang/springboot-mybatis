package com.zzh.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @Author: zzh
 * @Description:Session工具类
 * @Date: 2018/12/19
 */
public class SessionUtils {

    /**
     * 获取当前会话的token
     *
     * @return
     */
    public static String getCurrentUserName() {
        return String.valueOf(SecurityUtils.getSubject().getPrincipal());
    }

    /**
     * 获取当前会话的session
     *
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void setAttribute(String name, Object value) {
        getSession().setAttribute(name, value);
    }

    public static Object getAttribute(String name) {
        return getSession().getAttribute(name);
    }
}
