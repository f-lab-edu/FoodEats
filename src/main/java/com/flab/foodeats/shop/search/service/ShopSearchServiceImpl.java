package com.flab.foodeats.shop.search.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.shop.search.mapper.ShopSearchMapper;

@Service
public class ShopSearchServiceImpl implements ShopSearchService {

	private ShopSearchMapper shopSearchMapper;
	private ShopSearchErrorCheck shopSearchErrorCheck;

	public ShopSearchServiceImpl(ShopSearchMapper shopSearchMapper, ShopSearchErrorCheck shopSearchErrorCheck) {
		this.shopSearchMapper = shopSearchMapper;
		this.shopSearchErrorCheck = shopSearchErrorCheck;
	}

	@Override
	public ApiResponse findShopByCategory(String category) {
		shopSearchErrorCheck.resultEmptyCheck(shopSearchMapper.findShopByCategory(category));
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByCategory(category));
		return apiResponse;
	}

	@Override
	public ApiResponse findShopByBrand(String brand) {
		shopSearchErrorCheck.resultEmptyCheck(shopSearchMapper.findShopByBrand(brand));
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByBrand(brand));
		return apiResponse;
	}
}
