package org.basicData.config;

import jakarta.servlet.FilterRegistration;
import org.basicData.filter.CheckPermission;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CheckPermission> checkPermissionFilterRegistrationBean() {
        FilterRegistrationBean<CheckPermission> checkPermissionFilterRegistrationBean = new FilterRegistrationBean<>();
        checkPermissionFilterRegistrationBean.setFilter(new CheckPermission());
        checkPermissionFilterRegistrationBean.addUrlPatterns("/api/*");
        return  checkPermissionFilterRegistrationBean;
    }
}
