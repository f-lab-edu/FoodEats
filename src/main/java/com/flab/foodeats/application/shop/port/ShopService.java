package com.flab.foodeats.application.shop.port;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;

@Service
public interface ShopService {

	// 기본정보
	void registerEssentialShopInfo(EssentialTarget target);
	void updateEssentialShopInfo(EssentialTarget target);
	void deleteEssentialShopInfo(DeleteEssentialTarget target);

	// 상태정보
	void registerStatusShopInfo(StatusTarget target);



}
