package com.flab.foodeats.api.menu.adapter;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.domain.user.UserType;

@RestController
@RequestMapping("/shop")
public class MenuOptionController {

	private final MenuOptionService menuOptionService;

	public MenuOptionController(MenuOptionService menuOptionService) {
		this.menuOptionService = menuOptionService;
	}

	// 특정 메뉴에 옵션 등록
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/menu-option/{menuId}")
	public ResponseEntity<?> registerMenu(@PathVariable int menuId, @Valid @RequestBody OptionRequest request) {
		menuOptionService.registerMenuOption(menuId, request.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, "성공");
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	// 특정 메뉴의 옵션 검색
	@AuthRequired(role = UserType.MERCHANT)
	@GetMapping("/menu-option/{menuId}")
	public ResponseEntity<?> searchMenuOption(@PathVariable("menuId") int menuId) {
		List<MenuOption> menuOptions = menuOptionService.searchMenuOption(menuId);
		ApiResponse apiResponse = ApiResponse.responseData(StatusCode.SUCCESS, "성공", menuOptions);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 메뉴 옵션 수정
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/menu-option/{menuOptionId}")
	public ResponseEntity<?> updateMenuOption(
		@PathVariable int menuOptionId, @Valid @RequestBody OptionRequest request) {
		menuOptionService.updateMenuOption(menuOptionId, request.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, "성공");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 메뉴 옵션 삭제
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/menu-option/{menuOptionId}")
	public ResponseEntity<?> deleteMenuOption(@PathVariable int menuOptionId) {
		menuOptionService.deleteMenuOption(menuOptionId);
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, "성공");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}