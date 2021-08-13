package com.flab.foodeats.shop.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.shop.model.EssentialShopInfo;
import com.flab.foodeats.shop.model.StatusShopInfo;
import com.flab.foodeats.user.interceptor.auth.ShopAuth;

@Service
public interface ShopService {

	// 기본정보
	ApiResponse registerEssentialShopInfo(EssentialShopInfo essentialShopInfo, ShopAuth shopInfoStoredInSession);
	
	ApiResponse updateBasicShopInfo(EssentialShopInfo essentialShopInfo, ShopAuth shopInfoStoredInSession);
	
	ApiResponse deleteBasicShopInfo(ShopAuth shopInfoStoredInSession);

	// 상태정보
	ApiResponse registerDetailShopInfo(StatusShopInfo statusShopInfo,ShopAuth shopInfoStoredInSession);

	ApiResponse updateDetailShopInfo(StatusShopInfo statusShopInfo,ShopAuth shopInfoStoredInSession);

	// 전체조회
	ApiResponse searchShopAllInfo();
}
