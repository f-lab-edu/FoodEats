package com.flab.foodeats.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.shop.model.ShopDeleteDTO;
import com.flab.foodeats.shop.model.ShopInfoListByIdDTO;
import com.flab.foodeats.shop.model.ShopOpenStatusDTO;
import com.flab.foodeats.shop.model.ShopRegistrationDTO;
import com.flab.foodeats.shop.model.ShopUpdateDTO;
import com.flab.foodeats.shop.service.ShopService;
import com.flab.foodeats.global.ApiResponse;

@RestController
@RequestMapping("/shop")
public class ShopControllerImpl implements ShopController {

	private ShopService shopService;

	public ShopControllerImpl(ShopService shopService) {
		this.shopService = shopService;
	}

	@Override
	@PostMapping("/join")
	public ResponseEntity<?> registrationShop(ShopRegistrationDTO shopRegistrationDTO) {
		ApiResponse apiResponse = shopService.ShopRegistration(shopRegistrationDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	@PutMapping("/updated")
	public ResponseEntity<?> updateShop(ShopUpdateDTO shopUpdateDTO) {
		ApiResponse apiResponse = shopService.ShopUpdate(shopUpdateDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteShop(ShopDeleteDTO shopDeleteDTO) {
		ApiResponse apiResponse = shopService.ShopDelete(shopDeleteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	@GetMapping("/AllInfo")
	public ResponseEntity<?> searchShopAllInfo() {
		ApiResponse apiResponse = shopService.searchShopAllInfo();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> searchShopOneInfoById(ShopInfoListByIdDTO shopInfoListByIdDTO) {
		ApiResponse apiResponse = shopService.searchShopOneInfoById(shopInfoListByIdDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	@PutMapping("/start")
	public ResponseEntity<?> startShop(ShopOpenStatusDTO shopOpenStatusDTO) {
		ApiResponse apiResponse = shopService.startShop(shopOpenStatusDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	@PutMapping("/close")
	public ResponseEntity<?> closeShop(ShopOpenStatusDTO shopOpenStatusDTO) {
		ApiResponse apiResponse = shopService.closeShop(shopOpenStatusDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
