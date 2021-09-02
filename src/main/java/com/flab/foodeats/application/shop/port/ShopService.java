package com.flab.foodeats.application.shop.port;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.foodeats.application.shop.DeleteEssentialTarget;
import com.flab.foodeats.application.shop.ConvenientTarget;
import com.flab.foodeats.application.shop.EssentialTarget;
import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.domain.shop.Essential;

@Service
public interface ShopService {


	// 기본정보
	void registerEssentialShopInfo(AuthInfo authInfo, EssentialTarget target);
	void updateEssentialShopInfo(AuthInfo authInfo, EssentialTarget target);
	void deleteEssentialShopInfo(AuthInfo authInfo, DeleteEssentialTarget target);

	// 상태정보
	void registerStatusShopInfo(AuthInfo authInfo, StatusTarget target);
	void updateStatusShopInfo(AuthInfo authInfo, StatusTarget target);

	// 편리정보
	void registerConvenienceShopInfo(AuthInfo authInfo, ConvenientTarget target);
	void updateConvenienceShopInfo(AuthInfo authInfo, ConvenientTarget target);

	// 전체조회
	List<Essential> searchShopAllInfo(StatusTarget target);
}
