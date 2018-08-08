package com.common.shiro.mult.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by jackiechan on 18-7-27/上午9:36
 */
public class SecondRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.err.println("second授权");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //先根据用户名查询数据,获取到密码和盐
        ByteSource admin = ByteSource.Util.bytes("admin");
        String pass = "c34af346c89b8b03438e27a32863c9b5";

        SimpleAuthenticationInfo simpleAuthenticationInfo =new SimpleAuthenticationInfo("admin",pass,admin,getName());
        return simpleAuthenticationInfo;
    }
}
