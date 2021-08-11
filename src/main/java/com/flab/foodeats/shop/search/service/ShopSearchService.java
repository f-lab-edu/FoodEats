package com.flab.foodeats.shop.search.service;

import com.flab.foodeats.global.ApiResponse;

public interface ShopSearchService {

	ApiResponse findShopByCategory(String category);

	ApiResponse findShopByBrand(String brand);
}
