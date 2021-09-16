package com.flab.foodeats.application.shop.port;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.shop.DeleteShopInfoTarget;
import com.flab.foodeats.application.shop.ShopDeliveryTarget;
import com.flab.foodeats.application.shop.ShopInfoTarget;
import com.flab.foodeats.application.shop.BusinessHourTarget;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.domain.shop.Shop;

@Service
public interface ShopService {


	// 기본정보
	void registerShopInfo(AuthInfo authInfo, ShopInfoTarget target);
	void updateShopInfo(AuthInfo authInfo, ShopInfoTarget target);
	void deleteShopInfo(AuthInfo authInfo, DeleteShopInfoTarget target);

	// 상태정보
	void registerBusinessHour(AuthInfo authInfo, BusinessHourTarget target);
	void updateBusinessHour(AuthInfo authInfo, BusinessHourTarget target);

	// 편리정보
	void registerShopDeliveryInfo(AuthInfo authInfo, ShopDeliveryTarget target);
	void updateShopDeliveryInfo(AuthInfo authInfo, ShopDeliveryTarget target);

	// 전체조회
	List<Shop> searchShopAllInfo(BusinessHourTarget target);
}
