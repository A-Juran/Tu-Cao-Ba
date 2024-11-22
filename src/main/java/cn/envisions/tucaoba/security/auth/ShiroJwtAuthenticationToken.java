package cn.envisions.tucaoba.security.auth;


import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * @author JuRan
 * @date 2024/11/22 10:45
 * @description: ShiroJwt登录类
 */
public class ShiroJwtAuthenticationToken implements AuthenticationToken, HostAuthenticationToken {
    private final String token;

    public ShiroJwtAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String getHost() {
        return "";
    }
}
