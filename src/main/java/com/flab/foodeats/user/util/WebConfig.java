package com.flab.foodeats.user.util;

import com.flab.foodeats.user.filter.FilterAuth;
import com.flab.foodeats.user.filter.LogFilter;
import com.flab.foodeats.user.interceptor.LogInterceptor;
import com.flab.foodeats.user.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 인터셉터 구현
     * 로그인 인증
     *
     * 인터셉터1. 로그
     * 인터셉터2. 로그인 인증
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/","/user/login","/user/insert","/user/logout","/user/all","/user/findinfo"
                );
    }


    /**
     * 필터 구현
     * 로그인 인증
     *
     * 필터1. 로그
     * 필터2. 로그인 인증
     */

    // 로그 필터
    //@Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    // 로그인 인증 구현
    //@Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new FilterAuth());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}