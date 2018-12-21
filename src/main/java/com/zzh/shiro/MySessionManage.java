package com.zzh.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/12/20
 */
public class MySessionManage extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public MySessionManage() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (StringUtils.isEmpty(sessionId)) {
            sessionId = WebUtils.toHttp(request).getParameter(AUTHORIZATION);
        }
        if (!StringUtils.isEmpty(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, "true");
        } else {
            return super.getSessionId(request, response);
        }

        return super.getSessionId(request, response);
    }
}
