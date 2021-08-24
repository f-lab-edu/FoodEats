package com.flab.foodeats.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flab.foodeats.domain.user.auth.AuthArgumentResolver;
import com.flab.foodeats.domain.user.auth.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	AuthInterceptor authInterceptor;

	@Autowired
	AuthArgumentResolver authArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
			.order(1)
			.addPathPatterns("/**")
			.excludePathPatterns();
	}

}