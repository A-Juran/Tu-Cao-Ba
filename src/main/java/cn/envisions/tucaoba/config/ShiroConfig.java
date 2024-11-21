package cn.envisions.tucaoba.config;

import cn.envisions.tucaoba.config.relam.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Bean(name = "filterShiroFilterRegistrationBean")
    @Bean(name = "filterShiroFilterRegistrationBean")
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        logger.info("启动shiroFilter--时间是：{}", new Date());
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiro拦截器
        Map<String, String> filterMap = new LinkedHashMap<>();
        //放行
        filterMap.put("/user/login", "anon");
//        filterMap.put("/user/register", "anon");
//        //配置需要认证权限的
        filterMap.put("/**", "authc");
        logger.info("授权信息：{}",filterMap);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //自定义身份认证Realm（包含用户名密码校验，权限校验等）
    @Bean
    public ShiroRealm myShiroRealm() {
        return new ShiroRealm();
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //开启shiro aop注解支持，不开启的话权限验证就会失效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}