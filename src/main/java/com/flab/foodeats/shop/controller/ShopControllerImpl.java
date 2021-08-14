package com.flab.foodeats.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.shop.model.EssentialShopInfo;
import com.flab.foodeats.shop.model.StatusShopInfo;
import com.flab.foodeats.shop.service.ShopService;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;

@RestController
@RequestMapping("/shop")
public class ShopControllerImpl implements ShopController {

	private ShopService shopService;

	public ShopControllerImpl(ShopService shopService) {
		this.shopService = shopService;
	}

	// 가맹점 등록 (필수정보)
	@PostMapping("/essentialInfo/register")
	public ResponseEntity<?> registerEssentialShopInfo(EssentialShopInfo essentialShopInfo) {
		ApiResponse apiResponse = shopService.registerEssentialShopInfo(essentialShopInfo, AuthSessionControl.getShopAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	// 가맹점 수정 (필수정보)
	@PutMapping("/essentialInfo/update")
	public ResponseEntity<?> updateBasicShopInfo(EssentialShopInfo essentialShopInfo) {
		ApiResponse apiResponse = shopService.updateBasicShopInfo(essentialShopInfo, AuthSessionControl.getShopAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	// 가맹점 삭제 (필수정보)
	@DeleteMapping("/essentialInfo/delete")
	public ResponseEntity<?> deleteBasicShopInfo(EssentialShopInfo essentialShopInfo) {
		ApiResponse apiResponse = shopService.deleteBasicShopInfo(AuthSessionControl.getShopAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}


	// 가맹점 등록 (상태정보)
	@PostMapping("/statusInfo/register")
	public ResponseEntity<?> registerDetailShopInfo(StatusShopInfo statusShopInfo) {
		ApiResponse apiResponse = shopService.registerDetailShopInfo(statusShopInfo, AuthSessionControl.getShopAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
	// 가맹점 수정 (상태정보)
	@PutMapping("/statusInfo/update")
	public ResponseEntity<?> updateDetailShopInfo(StatusShopInfo statusShopInfo) {
		ApiResponse apiResponse = shopService.updateDetailShopInfo(statusShopInfo, AuthSessionControl.getShopAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}


	// 가맹점 기본정보 전체 조회
	@GetMapping("/AllInfo")
	public ResponseEntity<?> searchShopAllInfo() {
		ApiResponse apiResponse = shopService.searchShopAllInfo();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
