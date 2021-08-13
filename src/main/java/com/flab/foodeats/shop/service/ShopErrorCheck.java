package com.flab.foodeats.shop.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.flab.foodeats.shop.model.code.ErrorShopCode;

@Service
public class ShopErrorCheck {

	// 가맹점 기본정보 등록 - ShopId 중복 등록 차단
	// 가맹점 상세정보 등록 - ShopId 중복 등록 차단
	// 가맹점 편리정보 등록 - ShopId 중복 등록 차단
	public void ShopInfoAlreadyExist(Integer shopId) {
		if (shopId != null) {
			throw new DuplicateKeyException(ErrorShopCode.SHOP_EXIST.getMessage());
		}
	}
}
