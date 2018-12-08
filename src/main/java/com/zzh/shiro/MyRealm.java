package com.zzh.shiro;

import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.po.User;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zzh
 * @Description:处理用户合法
 * @Date: 2018/12/8
 */
@Service
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleModuleService userRoleModuleService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUserName(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
        User user = userService.selectByUserName(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JWTUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = JWTUtil.getUserName(principalCollection.toString());
        User user = userService.selectByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加用户的角色和权限
        UserRoleModuleDTO userRoleModuleDTO = userRoleModuleService.selectRolesModulesByUid(user.getUid());
        simpleAuthorizationInfo.addRoles(userRoleModuleDTO.getRnameSet());
        simpleAuthorizationInfo.addStringPermissions(userRoleModuleDTO.getMnameSet());
        return simpleAuthorizationInfo;
    }


}
