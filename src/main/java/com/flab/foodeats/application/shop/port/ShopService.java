package com.flab.foodeats.application.shop.port;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.ConvenientTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.domain.shop.Essential;

@Service
public interface ShopService {

	// 기본정보
	void registerEssentialShopInfo(EssentialTarget target);
	void updateEssentialShopInfo(EssentialTarget target);
	void deleteEssentialShopInfo(DeleteEssentialTarget target);

	// 상태정보
	void registerStatusShopInfo(StatusTarget target, String userId);
	void updateStatusShopInfo(StatusTarget target, String userId);

	// 편리정보
	void registerConvenienceShopInfo(ConvenientTarget target, String userId);
	void updateConvenienceShopInfo(ConvenientTarget target, String userId);

	// 전체조회
	List<Essential> searchShopAllInfo(StatusTarget target, String userId);

}
