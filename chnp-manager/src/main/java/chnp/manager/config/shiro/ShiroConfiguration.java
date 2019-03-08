package chnp.manager.config.shiro;

import chnp.manager.config.shiro.realm.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean
    public CustomRealm getCustomRealm() {
        return new CustomRealm();
    }

	/*@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
	}*/

    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getCustomRealm());
		//manager.setCacheManager(ehCacheManager());
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean getFilterFactory(SecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);

        factoryBean.setSuccessUrl("/index");
        factoryBean.setLoginUrl("/login");
        //factoryBean.setUnauthorizedUrl("/noauth");

        // 配置过滤规则
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "logout");
        filterMap.put("/logining", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/verification", "anon");
        filterMap.put("/portal/**", "anon");
        filterMap.put("/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }


    /**<p>开启注解支持</p>
     *
     * @param securityManager 管理器
     * @return 建议
     */
    /*@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }*/

}