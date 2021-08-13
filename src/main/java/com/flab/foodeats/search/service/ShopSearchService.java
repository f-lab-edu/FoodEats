package com.flab.foodeats.search.service;

import com.flab.foodeats.global.ApiResponse;

public interface ShopSearchService {

	ApiResponse findShopByCategory(String id, String category);

	ApiResponse findShopByBrand(String id, String brand);
}
