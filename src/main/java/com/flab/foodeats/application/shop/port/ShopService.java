package com.flab.foodeats.application.shop.port;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.shop.RegisterEssentialTarget;

@Service
public interface ShopService {

	// 기본정보
	void registerEssentialShopInfo(RegisterEssentialTarget target);




}
