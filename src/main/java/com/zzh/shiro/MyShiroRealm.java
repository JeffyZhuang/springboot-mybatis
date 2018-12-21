package com.zzh.shiro;

import com.zzh.dto.UserRoleModuleDTO;
import com.zzh.po.User;
import com.zzh.service.UserRoleModuleService;
import com.zzh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * @Author: zzh
 * @Description:
 * @Date: 2018/12/20
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleModuleService userRoleModuleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("----------执行shiro权限认证-----------");
        String userName = (String) super.getAvailablePrincipal(principalCollection);
        User user = userService.selectByUserName(userName);
        UserRoleModuleDTO userRoleModuleDTO = userRoleModuleService.selectRolesModulesByUid(user.getUid());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        HashSet hashSet = new HashSet();
        hashSet.add(userRoleModuleDTO.getRnameSet());
        simpleAuthorizationInfo.addRoles(hashSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectByUserName(token.getUsername());
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }
}
