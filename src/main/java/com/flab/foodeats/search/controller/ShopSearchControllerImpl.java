package com.flab.foodeats.search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.search.service.ShopSearchService;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;

@RestController
public class ShopSearchControllerImpl {

	private ShopSearchService shopSearchService;

	public ShopSearchControllerImpl(ShopSearchService shopSearchService) {
		this.shopSearchService = shopSearchService;
	}

	@GetMapping("/shop/category/{category}")
	public ResponseEntity<?> findShopByCategory(@PathVariable String category) {
		ApiResponse apiResponse = shopSearchService.findShopByCategory(AuthSessionControl.getAuthentication(), category);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/shop/brand/{brand}")
	public ResponseEntity<?> findShopByBrand(@PathVariable String brand) {
		ApiResponse apiResponse = shopSearchService.findShopByBrand(AuthSessionControl.getAuthentication(), brand);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
