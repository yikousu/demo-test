package com.hac.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hac
 * @date 2025/5/12 16:40
 */

/**
 * WebMvcConfigurer 是对 Spring MVC 自动配置的“增强器”
 * 配置拦截器
 * 配置跨域
 * 配置静态资源映射
 * ...
 */
@Configuration
public class SpringMvcCfg implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInterceptor myInterceptor = new MyInterceptor();
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }
}
