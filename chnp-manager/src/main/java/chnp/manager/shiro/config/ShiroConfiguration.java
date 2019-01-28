package chnp.manager.shiro.config;

import chnp.manager.shiro.realm.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

//    @Bean
//    public CustomRealm getCustomRealm() {
//        return new CustomRealm();
//    }

    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //manager.setRealm(getCustomRealm());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean getFilterFactory(SecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        //factoryBean.setLoginUrl("/login");
        //factoryBean.setUnauthorizedUrl("/noauth");

        // 配置过滤规则
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/logining", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/verification", "anon");
        filterMap.put("/portal/**", "anon");
        filterMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }

}