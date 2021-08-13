package com.flab.foodeats.search.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flab.foodeats.search.interceptor.ShopSearchInterceptor;

@Configuration
public class ShopSearchWebConfig implements WebMvcConfigurer {

	@Autowired
	ShopSearchInterceptor shopSearchInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shopSearchInterceptor)
			.order(1)
			.addPathPatterns("/shop/category/**", "/shop/brand/**")
			.excludePathPatterns();
	}
}
