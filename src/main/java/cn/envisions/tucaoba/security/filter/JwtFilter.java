package cn.envisions.tucaoba.security.filter;

import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.entity.domain.LoginUser;
import cn.envisions.tucaoba.security.auth.ShiroJwtAuthenticationToken;
import cn.envisions.tucaoba.security.service.TokenService;
import cn.envisions.tucaoba.utils.StringUtils;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
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
 * @author: lhy
 * jwt过滤器，作为shiro的过滤器，对请求进行拦截并处理
 * 跨域配置不在这里配了，我在另外的配置类进行配置了，这里把重心放在验证上
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Autowired
    TokenService tokenService;

    /**
     * 进行token的验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        LoginUser loginUser = getLoginUser((HttpServletRequest) request);
        log.info("执行登录:{},{}",StringUtils.isNull(loginUser),"执行login");
        if (StringUtils.isNull(loginUser)) {
            out((HttpServletResponse) response);
            return false;
        }
        //token存在，进行验证
        ShiroJwtAuthenticationToken jwtToken = new ShiroJwtAuthenticationToken(loginUser.getUserAndPasswordToken());
        try {
            SecurityUtils.getSubject().login(jwtToken);  //通过subject，提交给myRealm进行登录验证
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * json形式返回结果token验证失败信息，无需转发
     */
    private void out(HttpServletResponse response) throws IOException {
        // 设置响应内容类型和编码
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
    }

    /**
     * 过滤器拦截请求的入口方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public LoginUser getLoginUser(HttpServletRequest servletRequest) {
        return tokenService.getLoginUser(servletRequest);
    }

}