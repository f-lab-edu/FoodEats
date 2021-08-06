package com.flab.foodeats.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ShopSearchController {

	@GetMapping("/shop/all")
	public ResponseEntity<?> findAllShop();

	@GetMapping("/shop/open")
	public ResponseEntity<?> findShopIsOpen();

	@GetMapping("/shop/location/{location}")
	public ResponseEntity<?> findShopByLocation(@PathVariable String location);

	@GetMapping("/shop/shopId/{shopId}")
	public ResponseEntity<?> findShopById(@PathVariable int shopId);

	@GetMapping("/shop/category/{category}")
	public ResponseEntity<?> findShopByCategory(@PathVariable String category);

	@GetMapping("/shop/brand/{brand}")
	public ResponseEntity<?> findShopByBrand(@PathVariable String brand);

}
