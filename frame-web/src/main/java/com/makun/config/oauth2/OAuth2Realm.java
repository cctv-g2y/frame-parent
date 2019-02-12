package com.makun.config.oauth2;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.makun.entity.system.SysUser;
import com.makun.service.system.SysUserService;

/**
 * @说明：认证
 * @author: makun
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    // @Autowired
    // private shiroService shiroService;

    @Autowired
    private SysUserService userService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        String userId = user.getUserId();
        System.out.println("验证权限：" + userId);

        // 用户权限列表
        // Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());

        // 用户信息
        SysUser user = userService.getUser(userName);

        // 账号不存在、密码错误
        if (null == user || StringUtils.isEmpty(user)) {
            throw new AccountException("用户不存在");
        } else if ("0".equals(user.getDeleteFlag())) {
            throw new AccountException("用户不存在");
        } else if (!user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            throw new AccountException("密码错误");
        } else if (!"1".equals(user.getStatus())) {
            throw new AccountException("账号已被锁定，请联系管理员");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}