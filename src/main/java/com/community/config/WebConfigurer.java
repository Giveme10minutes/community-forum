package com.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private UserIdHandlerArgumentResolver userIdHandlerArgumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/api/test")
                .excludePathPatterns("/post/**")
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
                .excludePathPatterns("/api/swagger-ui/**", "/api/v3/api-docs/**", "/api/swagger-resources/**", "/api/webjars/**")
                .order(1);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIdHandlerArgumentResolver);
    }
}
