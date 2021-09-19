package com.flab.foodeats.api.menu.adapter;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.menu.EssentialMenuRequest;
import com.flab.foodeats.api.menu.MenuCode;
import com.flab.foodeats.application.menu.port.MenuService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
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
	@PostMapping("/{shopId}/menu")
	public ResponseEntity<?> registerMenu(@PathVariable Long shopId,
										  @Valid @RequestBody EssentialMenuRequest request,
										  @AuthUsed AuthInfo authInfo) {
		menuService.registerMenu(request.toParam(shopId), authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, MenuCode.MENU_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	// 메뉴 수정
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/{shopId}/menu/{menuId}")
	public ResponseEntity<?> updateMenu(@PathVariable Long shopId, @PathVariable Long menuId,
										@Valid @RequestBody EssentialMenuRequest request,
										@AuthUsed AuthInfo authInfo) {
		menuService.modifyMenu(request.toParam(shopId, menuId), authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, MenuCode.MENU_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 메뉴 삭제
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/{shopId}/menu/{menuId}")
	public ResponseEntity<?> deleteMenu(@PathVariable Long shopId, @PathVariable Long menuId,
										@AuthUsed AuthInfo authInfo) {
		menuService.deleteMenu(shopId, menuId, authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, MenuCode.MENU_DELETE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
