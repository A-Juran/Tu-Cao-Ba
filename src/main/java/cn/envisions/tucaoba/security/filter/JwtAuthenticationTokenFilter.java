package cn.envisions.tucaoba.security.filter;

import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.entity.domain.LoginUser;
import cn.envisions.tucaoba.security.auth.ShiroJwtAuthenticationToken;
import cn.envisions.tucaoba.security.service.TokenService;
import cn.envisions.tucaoba.utils.StringUtils;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author JuRan
 * @date 2024/11/22 10:06
 * @description: Token拦截器
 */
public class JwtAuthenticationTokenFilter extends BasicHttpAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private TokenService tokenService;


    private void executeJwtAuthentication(LoginUser loginUser, ServletRequest request, ServletResponse response) throws AuthenticationException {
        //验证并刷新Token
        tokenService.verifyToken(loginUser);
        // 委托给Realm进行认证和授权
        getSubject(request, response).login(new ShiroJwtAuthenticationToken(loginUser.getUserAndPasswordToken()));
    }

    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse servletResponse, Object mappedValue) {
        // 获取登录用户
        LoginUser loginUser = getLoginUser((HttpServletRequest) request);
        log.info("拦截器解析 token 内容：{}, {}", StringUtils.isNotNull(loginUser), loginUser);
        // 判断用户是否为空
        if (StringUtils.isNull(loginUser)) {
            // 设置响应内容类型和编码
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置状态码为401未授权
            //设置响应编码
            AjaxResult error = AjaxResult.error(HttpServletResponse.SC_UNAUTHORIZED, "请登录后尝试");
            // 输出JSON
            try (ServletOutputStream out = response.getOutputStream()) {
                try {
                    out.write(JSONObject.toJSONString(error).getBytes(StandardCharsets.UTF_8)); // 明确指定 UTF-8 编码
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                out.flush();
            }
            return false;
        }
        executeJwtAuthentication(loginUser, request, servletResponse);
        return true;
    }


    public LoginUser getLoginUser(HttpServletRequest servletRequest) {
        return tokenService.getLoginUser(servletRequest);
    }
}
