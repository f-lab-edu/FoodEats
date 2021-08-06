package com.flab.foodeats.shop.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.shop.mapper.ShopMapper;
import com.flab.foodeats.shop.model.ShopDeleteDTO;
import com.flab.foodeats.shop.model.ShopInfoListByIdDTO;
import com.flab.foodeats.shop.model.ShopOpenStatusDTO;
import com.flab.foodeats.shop.model.ShopRegistrationDTO;
import com.flab.foodeats.shop.model.ShopUpdateDTO;
import com.flab.foodeats.shop.model.code.SuccessShopCode;
import com.flab.foodeats.global.ApiResponse;

@Service
public class ShopServiceImpl implements ShopService {

	private ShopMapper shopMapper;
	private ShopErrorCheck shopErrorCheck;

	public ShopServiceImpl(ShopMapper shopMapper, ShopErrorCheck shopErrorCheck) {
		this.shopMapper = shopMapper;
		this.shopErrorCheck = shopErrorCheck;
	}

	@Override
	public ApiResponse ShopRegistration(ShopRegistrationDTO shopRegistrationDTO) {
		shopErrorCheck.ShopInfoAlreadyExist(shopMapper.findShopById(shopRegistrationDTO.getShopId()));
		shopMapper.registrationShop(shopRegistrationDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_JOIN_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse ShopUpdate(ShopUpdateDTO shopUpdateDTO) {
		shopMapper.updateShop(shopUpdateDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_UPDATE_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse ShopDelete(ShopDeleteDTO shopDeleteDTO) {
		shopMapper.deleteShop(shopDeleteDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_DELETE_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse searchShopAllInfo() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopMapper.ShopListAllInfo());
		return apiResponse;
	}

	@Override
	public ApiResponse searchShopOneInfoById(ShopInfoListByIdDTO shopInfoListByIdDTO) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopMapper.ShopListOneInfo("3"));
		return apiResponse;
	}

	@Override
	public ApiResponse startShop(ShopOpenStatusDTO shopOpenStatusDTO) {
		shopMapper.startShop(shopOpenStatusDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_OPEN_SUCCESS);
		return apiResponse;
	}

	@Override
	public ApiResponse closeShop(ShopOpenStatusDTO shopOpenStatusDTO) {
		shopMapper.closeShop(shopOpenStatusDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_CLOSE_SUCCESS);
		return apiResponse;
	}
}
