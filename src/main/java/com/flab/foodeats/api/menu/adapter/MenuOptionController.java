package com.flab.foodeats.api.menu.adapter;

import java.util.List;

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

import com.flab.foodeats.api.menu.MenuCode;
import com.flab.foodeats.api.menu.OptionRequest;
import com.flab.foodeats.application.menu.port.MenuOptionService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;
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
	@PostMapping("/{shopId}/menu-option/{menuId}")
	public ResponseEntity<?> registerMenuOption(@PathVariable Long shopId, @PathVariable Long menuId,
												@RequestBody @Valid List<OptionRequest> optionRequests,
												@AuthUsed AuthInfo authInfo) {
		menuOptionService.registerMenuOption(shopId, menuId, optionRequests, authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, MenuCode.OPTION_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}

	// 메뉴 옵션 수정
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/{shopId}/menu-option/{menuOptionId}")
	public ResponseEntity<?> updateMenuOption(@PathVariable Long shopId, @PathVariable Long menuOptionId,
											  @RequestBody @Valid OptionRequest optionRequest,
											  @AuthUsed AuthInfo authInfo) {
		menuOptionService.updateMenuOption(shopId, optionRequest.toParam(menuOptionId), authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, MenuCode.OPTION_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 메뉴 옵션 삭제
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/{shopId}/menu-option/{menuOptionId}")
	public ResponseEntity<?> deleteMenuOption(@PathVariable Long shopId, @PathVariable Long menuOptionId,
											  @AuthUsed AuthInfo authInfo) {
		menuOptionService.deleteMenuOption(shopId, menuOptionId, authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, MenuCode.OPTION_DELETE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
