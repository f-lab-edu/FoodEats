package com.flab.foodeats.shop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.shop.model.EssentialShopInfo;
import com.flab.foodeats.shop.model.ShopDeliveryLocation;

@RestController
@RequestMapping("/shop")
public interface ShopController {

	/**
	 * 가맹점 필수 정보
	 */
	@PostMapping("/essentialInfo/register")
	public ResponseEntity<?> registerEssentialShopInfo(@Valid @RequestBody EssentialShopInfo essentialShopInfo);
	@PutMapping("/essentialInfo/update")
	public ResponseEntity<?> updateBasicShopInfo(@Valid @RequestBody EssentialShopInfo essentialShopInfo);
	@DeleteMapping("/essentialInfo/delete")
	public ResponseEntity<?> deleteBasicShopInfo(@Valid @RequestBody EssentialShopInfo essentialShopInfo);

	/**
	 * 검색 및 조회
	 */
	@GetMapping("/AllInfo")
	public ResponseEntity<?> searchShopAllInfo();

	/**
	 * 배달가능 지역 등록
	 */
	@PostMapping("/register/deliveryLocation/{shopId}")
	ResponseEntity<?> registerShopDeliveryLocation(
		@PathVariable Long shopId, @Valid @RequestBody ShopDeliveryLocation shopDeliveryLocation);
}
