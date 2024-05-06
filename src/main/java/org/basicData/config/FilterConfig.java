package org.basicData.config;

import jakarta.servlet.FilterRegistration;
import org.basicData.filter.CheckPermission;
import org.basicData.service.AuthenticationServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Autowired
    private AuthenticationServiceProxy authenticationServiceProxy;
    @Bean
    public FilterRegistrationBean<CheckPermission> checkPermissionFilterRegistrationBean() {
        FilterRegistrationBean<CheckPermission> checkPermissionFilterRegistrationBean = new FilterRegistrationBean<>();
        checkPermissionFilterRegistrationBean.setFilter(new CheckPermission(authenticationServiceProxy));
        checkPermissionFilterRegistrationBean.addUrlPatterns("/api/*");
        return  checkPermissionFilterRegistrationBean;
    }
}
