package com.flab.foodeats.api.shop.adapter;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.shop.DeleteEssentialRequest;
import com.flab.foodeats.api.shop.RegisterEssentialRequest;
import com.flab.foodeats.api.shop.UpdateEssentialRequest;
import com.flab.foodeats.application.shop.port.ShopService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.domain.user.UserType;

@RestController
@RequestMapping("/shop")
public class ShopApi {

	private ShopService shopService;

	public ShopApi(ShopService shopService) {
		this.shopService = shopService;
	}

	// 가맹점 등록 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/essentialInfo/register")
	public ResponseEntity<?> registerEssentialInfo(@Valid @RequestBody RegisterEssentialRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.registerEssentialShopInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/essentialInfo/update")
	public ResponseEntity<?> updateEssentialInfo(@Valid @RequestBody UpdateEssentialRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.updateEssentialShopInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 삭제 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/essentialInfo/delete")
	public ResponseEntity<?> deleteEssentialInfo(@Valid @RequestBody DeleteEssentialRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.deleteEssentialShopInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_DELETE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}


}
