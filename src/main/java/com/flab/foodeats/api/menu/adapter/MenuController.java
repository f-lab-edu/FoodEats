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

import com.flab.foodeats.api.menu.EssentialMenuRequest;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.domain.user.UserType;

@RestController
@RequestMapping("/shop")
public class MenuController {

	private MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	// 가맹점 메뉴 등록
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/menu/{shopId}")
	public ResponseEntity<?> registerMenu(@PathVariable int shopId, @Valid @RequestBody EssentialMenuRequest request) {
		menuService.registerMenu(shopId, request.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_REGISTER_SUCCESS);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	// 가맹점별 메뉴 검색
	@AuthRequired(role = UserType.MERCHANT)
	@GetMapping("/menu/{shopId}")
	public ResponseEntity<?> searchMenu(@PathVariable int shopId) {
		List<Menu> menus = menuService.searchMenu(shopId);
		ApiResponse apiResponse = ApiResponse.responseData(StatusCode.SUCCESS, "성공", menus);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 메뉴 수정
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/menu/{menuId}")
	public ResponseEntity<?> updateMenu(@PathVariable int menuId, @Valid @RequestBody EssentialMenuRequest request) {
		menuService.modifyMenu(menuId, request.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, "성공");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 메뉴 삭제
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/menu/{menuId}")
	public ResponseEntity<?> deleteMenu(@PathVariable int menuId) {
		menuService.deleteMenu(menuId);
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, "성공");
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
