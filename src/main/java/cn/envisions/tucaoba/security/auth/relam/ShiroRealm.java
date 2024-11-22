package cn.envisions.tucaoba.security.auth.relam;

import cn.envisions.tucaoba.security.auth.ShiroJwtAuthenticationToken;
import cn.envisions.tucaoba.security.service.TokenService;
import cn.envisions.tucaoba.service.IUserService;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JuRan
 * @date 2024/11/21 11:44
 * @description: shiro认证、授权
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenService tokenService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        //解析token
        return new SimpleAuthenticationInfo(token, token, "jwt");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroJwtAuthenticationToken;
    }
}
