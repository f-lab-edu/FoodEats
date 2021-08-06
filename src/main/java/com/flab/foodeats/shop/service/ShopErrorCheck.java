package com.flab.foodeats.shop.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.flab.foodeats.shop.model.ShopRegistrationDTO;
import com.flab.foodeats.shop.model.code.ErrorShopCode;

@Service
public class ShopErrorCheck {

	public void ShopInfoAlreadyExist(ShopRegistrationDTO shopRegistrationDTO){
		if (shopRegistrationDTO != null) {
			throw new DuplicateKeyException(ErrorShopCode.SHOP_EXIST.getMessage());
		}
	}




}
