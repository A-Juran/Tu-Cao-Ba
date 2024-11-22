package cn.envisions.tucaoba.config;

import cn.envisions.tucaoba.config.relam.CustomCredentialsMatcher;
import cn.envisions.tucaoba.config.relam.ShiroRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager getManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //自定义filter

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
        filterMap.put("/**", "authc");
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


}