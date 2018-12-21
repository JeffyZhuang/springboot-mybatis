package com.zzh.shiro;

import com.alibaba.fastjson.JSON;
import com.zzh.ErrorCode;
import com.zzh.result.ApiResult;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Author: zzh
 * @Description:登陆认证过滤器
 * @Date: 2018/12/20
 */
public class MyAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object
            mappedValue) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        Serializable id = subject.getSession().getId();
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray.length == 0 || rolesArray == null) {
            return false;
        }

        for (String role : rolesArray) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        if (subject.getPrincipal() == null) {
            response.getWriter().print(JSON.toJSONString(ApiResult.fail(ErrorCode.USER_UNAUTH)));
        } else {
            response.getWriter().print(JSON.toJSONString(ApiResult.fail(ErrorCode.USER_UNAUTHORIZATION)));
        }
        return false;
    }
}
