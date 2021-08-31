package com.flab.foodeats.api.shop.adapter;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.shop.DeleteEssentialRequest;
import com.flab.foodeats.api.shop.ConvenientRequest;
import com.flab.foodeats.api.shop.EssentialRequest;
import com.flab.foodeats.api.shop.StatusRequest;
import com.flab.foodeats.application.shop.port.ShopService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.domain.shop.Essential;
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
	@PostMapping("/essential-info")
	public ResponseEntity<?> registerEssentialInfo(@Valid @RequestBody EssentialRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.registerEssentialShopInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/essential-info")
	public ResponseEntity<?> updateEssentialInfo(@Valid @RequestBody EssentialRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.updateEssentialShopInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 삭제 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/essential-info")
	public ResponseEntity<?> deleteEssentialInfo(DeleteEssentialRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.deleteEssentialShopInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_DELETE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 등록 (상태정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/status-info")
	public ResponseEntity<?> registerStatusInfo(@Valid @RequestBody StatusRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.registerStatusShopInfo(dto.toParam(),authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (상태정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/status-info")
	public ResponseEntity<?> updateStatusInfo(@Valid @RequestBody StatusRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.updateStatusShopInfo(dto.toParam(),authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 등록 (편리정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/convenience-info")
	public ResponseEntity<?> registerConvenienceInfo(@Valid @RequestBody ConvenientRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.registerConvenienceShopInfo(dto.toParam(),authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (편리정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/convenience-info")
	public ResponseEntity<?> updateConvenienceInfo(@Valid @RequestBody ConvenientRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.updateConvenienceShopInfo(dto.toParam(),authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 기본정보 전체 조회
	@GetMapping("/all-info")
	@AuthRequired(role = UserType.MERCHANT)
	public ResponseEntity<?> searchShopAllInfo(@AuthUsed AuthInfo authInfo) {
		List<Essential> list = shopService.searchShopAllInfo(new StatusRequest().toParam(),authInfo.getUserId());
		ApiResponse apiResponse = ApiResponse.responseList(StatusCode.SUCCESS, SuccessUserCode.USER_REGISTER_SUCCESS.getMessage(),list);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
