package com.flab.foodeats.shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flab.foodeats.shop.Interceptor.ShopInterceptor;

@Configuration
public class ShopWebConfig implements WebMvcConfigurer {

	@Autowired
	ShopInterceptor shopInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shopInterceptor)
			.order(1)
			.addPathPatterns("/shop/**")
			.excludePathPatterns();
	}

}
