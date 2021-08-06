package com.flab.foodeats.shop.service;

import com.flab.foodeats.global.ApiResponse;

public interface ShopSearchService {

	ApiResponse findAllShop();

	ApiResponse findShopById(int shopId);

	ApiResponse findShopByCategory(String category);

	ApiResponse findShopByBrand(String brand);

	ApiResponse findShopIsOpen();

	ApiResponse findShopByLocation(String location);
}
