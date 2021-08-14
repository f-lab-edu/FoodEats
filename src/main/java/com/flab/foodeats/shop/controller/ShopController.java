package com.flab.foodeats.shop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.shop.model.EssentialShopInfo;
import com.flab.foodeats.shop.model.StatusShopInfo;

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
	 * 가맹점 상태 정보
	 */
	@PostMapping("/statusInfo/register")
	public ResponseEntity<?> registerDetailShopInfo(@Valid @RequestBody StatusShopInfo statusShopInfo);
	@PutMapping("/statusInfo/update")
	public ResponseEntity<?> updateDetailShopInfo(@Valid @RequestBody StatusShopInfo statusShopInfo);


	/**
	 * 검색 및 조회
	 */
	@GetMapping("/AllInfo")
	public ResponseEntity<?> searchShopAllInfo();
}
