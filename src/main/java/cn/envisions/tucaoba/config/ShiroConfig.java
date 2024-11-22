package cn.envisions.tucaoba.config;

import cn.envisions.tucaoba.security.auth.relam.CustomCredentialsMatcher;
import cn.envisions.tucaoba.security.auth.relam.ShiroRealm;
import cn.envisions.tucaoba.security.filter.JwtAuthenticationTokenFilter;
import cn.envisions.tucaoba.security.filter.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //为了实现Shiro无状态登录，需要将会话滞空，这样在登录后不会保存会话，而通过拦截器进行认证处理。
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(defaultSubjectDAO);
        //realm
        manager.setRealm(shiroRealm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        //拦截器filter
        HashMap<String, Filter> requestFilterMap = new HashMap<>();
        requestFilterMap.put("jwt", getJwtFilter());
        factoryBean.setFilters(requestFilterMap);

        //自定义过滤器
        Map<String, String> filterMap = new HashMap<>();

        // 访问401和404页面不通过我们的Filter
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/register", "anon");
        // swagger
        filterMap.put("/doc.html", "anon");
        filterMap.put("/swagger-resources", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/401", "anon");
        filterMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }

    @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        return shiroRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    @Bean
//    public JwtAuthenticationTokenFilter getJwtAuthenticationTokenFilter(){
//        return new JwtAuthenticationTokenFilter();
//    }

    @Bean
    public JwtFilter getJwtFilter(){
        return new JwtFilter();
    }

    /**
     * 开启对 Shiro 注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}