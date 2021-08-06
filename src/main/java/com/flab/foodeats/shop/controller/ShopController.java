package com.flab.foodeats.shop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flab.foodeats.shop.model.ShopDeleteDTO;
import com.flab.foodeats.shop.model.ShopInfoListByIdDTO;
import com.flab.foodeats.shop.model.ShopOpenStatusDTO;
import com.flab.foodeats.shop.model.ShopRegistrationDTO;
import com.flab.foodeats.shop.model.ShopUpdateDTO;

public interface ShopController {

	@PostMapping("/join")
	public ResponseEntity<?> registrationShop(@Valid @RequestBody ShopRegistrationDTO shopRegistrationDTO);

	@PutMapping("/updated")
	public ResponseEntity<?> updateShop(@Valid @RequestBody ShopUpdateDTO shopUpdateDTO);

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteShop(@Valid @RequestBody ShopDeleteDTO shopDeleteDTO);

	@GetMapping("/AllInfo")
	public ResponseEntity<?> searchShopAllInfo();

	@GetMapping("/OneInfo")
	public ResponseEntity<?> searchShopOneInfoById(@Valid @RequestBody ShopInfoListByIdDTO shopInfoListByIdDTO);

	@PutMapping("/start")
	public ResponseEntity<?> startShop(@Valid @RequestBody ShopOpenStatusDTO shopOpenStatusDTO);

	@PutMapping("/close")
	public ResponseEntity<?> closeShop(@Valid @RequestBody ShopOpenStatusDTO shopOpenStatusDTO);

}
