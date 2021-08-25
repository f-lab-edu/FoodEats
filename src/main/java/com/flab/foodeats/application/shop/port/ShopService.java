package com.flab.foodeats.application.shop.port;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.RegisterEssentialTarget;
import com.flab.foodeats.application.shop.RegisterStatusTarget;
import com.flab.foodeats.application.shop.UpdateEssentialTarget;

@Service
public interface ShopService {

	// 기본정보
	void registerEssentialShopInfo(RegisterEssentialTarget target);
	void updateEssentialShopInfo(UpdateEssentialTarget target);
	void deleteEssentialShopInfo(DeleteEssentialTarget target);

	// 상태정보
	void registerStatusShopInfo(RegisterStatusTarget target);



}
