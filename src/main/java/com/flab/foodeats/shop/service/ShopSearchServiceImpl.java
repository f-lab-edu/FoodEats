package com.flab.foodeats.shop.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.shop.mapper.ShopSearchMapper;

@Service
public class ShopSearchServiceImpl implements ShopSearchService {

	private ShopSearchMapper shopSearchMapper;

	public ShopSearchServiceImpl(ShopSearchMapper shopSearchMapper) {
		this.shopSearchMapper = shopSearchMapper;
	}

	@Override
	public ApiResponse findAllShop() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findAllShop());
		return apiResponse;
	}

	@Override
	public ApiResponse findShopById(int shopId) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopById(shopId));
		return apiResponse;
	}

	@Override
	public ApiResponse findShopByCategory(String category) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByCategory(category));
		return apiResponse;
	}

	@Override
	public ApiResponse findShopByBrand(String brand) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByBrand(brand));
		return apiResponse;
	}

	@Override
	public ApiResponse findShopIsOpen() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopIsOpen());
		return apiResponse;
	}

	@Override
	public ApiResponse findShopByLocation(String location) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByLocation(location));
		return apiResponse;
	}
}
