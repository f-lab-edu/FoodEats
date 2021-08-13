package com.flab.foodeats.search.service;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.search.mapper.ShopSearchMapper;
import com.flab.foodeats.user.mapper.UserMapper;

@Service
public class ShopSearchServiceImpl implements ShopSearchService {

	private UserMapper userMapper;
	private ShopSearchMapper shopSearchMapper;
	private ShopSearchErrorCheck shopSearchErrorCheck;

	public ShopSearchServiceImpl(UserMapper userMapper,
		ShopSearchMapper shopSearchMapper,
		ShopSearchErrorCheck shopSearchErrorCheck) {
		this.userMapper = userMapper;
		this.shopSearchMapper = shopSearchMapper;
		this.shopSearchErrorCheck = shopSearchErrorCheck;
	}

	@Override
	public ApiResponse findShopByCategory(String id, String category) {
		String address = userMapper.findConsumerAddress(id);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByCategory(address, category));
		return apiResponse;
	}

	@Override
	public ApiResponse findShopByBrand(String id, String brand) {
		String address = userMapper.findConsumerAddress(id);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopSearchMapper.findShopByBrand(address, brand));
		return apiResponse;
	}
}
