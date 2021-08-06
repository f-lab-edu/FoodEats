package com.flab.foodeats.shop.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.shop.model.ShopDeleteDTO;
import com.flab.foodeats.shop.model.ShopInfoListByIdDTO;
import com.flab.foodeats.shop.model.ShopOpenStatusDTO;
import com.flab.foodeats.shop.model.ShopRegistrationDTO;
import com.flab.foodeats.shop.model.ShopUpdateDTO;
import com.flab.foodeats.global.ApiResponse;

@Service
public interface ShopService {

	ApiResponse ShopRegistration(ShopRegistrationDTO shopRegistrationDTO);

	ApiResponse ShopUpdate(ShopUpdateDTO shopUpdateDTO);

	ApiResponse ShopDelete(ShopDeleteDTO shopDeleteDTO);

	ApiResponse searchShopAllInfo();

	ApiResponse searchShopOneInfoById(ShopInfoListByIdDTO shopInfoListByIdDTO);

	ApiResponse startShop(ShopOpenStatusDTO shopOpenStatusDTO);

	ApiResponse closeShop(ShopOpenStatusDTO shopOpenStatusDTO);

}
