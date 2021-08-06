package com.flab.foodeats.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.shop.service.ShopSearchService;

@RestController
public class ShopSearchControllerImpl implements ShopSearchController {

	private ShopSearchService shopSearchService;

	public ShopSearchControllerImpl(ShopSearchService shopSearchService) {
		this.shopSearchService = shopSearchService;
	}

	@GetMapping("/shop/all")
	public ResponseEntity<?> findAllShop() {
		ApiResponse apiResponse = shopSearchService.findAllShop();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/shop/open")
	public ResponseEntity<?> findShopIsOpen() {
		ApiResponse apiResponse = shopSearchService.findShopIsOpen();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/shop/location/{location}")
	public ResponseEntity<?> findShopByLocation(@PathVariable String location) {
		ApiResponse apiResponse = shopSearchService.findShopByLocation(location);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/shop/shopId/{shopId}")
	public ResponseEntity<?> findShopById(@PathVariable int shopId) {
		ApiResponse apiResponse = shopSearchService.findShopById(shopId);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/shop/category/{category}")
	public ResponseEntity<?> findShopByCategory(@PathVariable String category) {
		ApiResponse apiResponse = shopSearchService.findShopByCategory(category);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@GetMapping("/shop/brand/{brand}")
	public ResponseEntity<?> findShopByBrand(@PathVariable String brand) {
		ApiResponse apiResponse = shopSearchService.findShopByBrand(brand);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
