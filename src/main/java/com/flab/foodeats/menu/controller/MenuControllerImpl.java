package com.flab.foodeats.menu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.menu.model.MenuEssentialParam;
import com.flab.foodeats.menu.model.MenuOptionParam;
import com.flab.foodeats.menu.model.MenuRegisterParam;
import com.flab.foodeats.menu.service.MenuService;

@RestController
@RequestMapping("/shop")
public class MenuControllerImpl implements MenuController {

	private MenuService menuService;

	public MenuControllerImpl(MenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	public ResponseEntity<?> registerMenu(int shopId, MenuRegisterParam menuRegisterParam) {
		ApiResponse apiResponse = menuService.registerMenu(shopId, menuRegisterParam);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> searchMenu(int shopId) {
		ApiResponse apiResponse = menuService.searchMenu(shopId);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> searchMenuOption(int menuId) {
		ApiResponse apiResponse = menuService.searchMenuOption(menuId);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> updateMenu(int menuId, MenuEssentialParam menuEssentialParam) {
		ApiResponse apiResponse = menuService.updateMenu(menuId, menuEssentialParam);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> deleteMenu(int menuId) {
		ApiResponse apiResponse = menuService.deleteMenu(menuId);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> updateMenuOption(int menuOptionId, MenuOptionParam menuOptionParam) {
		ApiResponse apiResponse = menuService.updateMenuOption(menuOptionId, menuOptionParam);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@Override
	public ResponseEntity<?> deleteMenuOption(int menuOptionId) {
		ApiResponse apiResponse = menuService.deleteMenuOption(menuOptionId);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
