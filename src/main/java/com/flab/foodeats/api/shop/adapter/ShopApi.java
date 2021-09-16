package com.flab.foodeats.api.shop.adapter;

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

import com.flab.foodeats.api.shop.DeleteShopInfoRequest;
import com.flab.foodeats.api.shop.ShopDeliveryRequest;
import com.flab.foodeats.api.shop.ShopInfoRequest;
import com.flab.foodeats.api.shop.BusinessHourRequest;
import com.flab.foodeats.application.shop.port.ShopService;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.domain.shop.Shop;
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
	@PostMapping("/{shopId}/shop-info")
	public ResponseEntity<?> registerShopInfo(@PathVariable Long shopId, @Valid @RequestBody ShopInfoRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.registerShopInfo(authInfo, dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/{shopId}/shop-info")
	public ResponseEntity<?> updateShopInfo(@PathVariable Long shopId, @Valid @RequestBody ShopInfoRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.updateShopInfo(authInfo, dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 삭제 (필수정보)
	@AuthRequired(role = UserType.MERCHANT)
	@DeleteMapping("/{shopId}/all-shop-table-info")
	public ResponseEntity<?> deleteShopInfo(@PathVariable Long shopId, DeleteShopInfoRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.deleteShopInfo(authInfo,dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_DELETE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 등록 (상태정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/{shopId}/business-hour")
	public ResponseEntity<?> registerBusinessHour(@PathVariable Long shopId, @Valid @RequestBody BusinessHourRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.registerBusinessHour(authInfo,dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (상태정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/{shopId}/business-hour")
	public ResponseEntity<?> updateBusinessHour(@PathVariable Long shopId ,@Valid @RequestBody BusinessHourRequest dto, @AuthUsed
		AuthInfo authInfo) {
		shopService.updateBusinessHour(authInfo,dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 등록 (편리정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PostMapping("/{shopId}/delivery")
	public ResponseEntity<?> registerShopDeliveryInfo(@PathVariable Long shopId, @Valid @RequestBody ShopDeliveryRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.registerShopDeliveryInfo(authInfo,dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 수정 (편리정보)
	@AuthRequired(role = UserType.MERCHANT)
	@PutMapping("/{shopId}/delivery")
	public ResponseEntity<?> updateShopDeliveryInfo(@PathVariable Long shopId, @Valid @RequestBody ShopDeliveryRequest dto,
		@AuthUsed AuthInfo authInfo) {
		shopService.updateShopDeliveryInfo(authInfo,dto.toParam(shopId));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.SHOP_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 기본정보 전체 조회
	@GetMapping("/all-info")
	@AuthRequired(role = UserType.MERCHANT)
	public ResponseEntity<?> searchShopAllInfo(@AuthUsed AuthInfo authInfo) {
		List<Shop> list = shopService.searchShopAllInfo(new BusinessHourRequest().toParam(authInfo.getId()));
		ApiResponse apiResponse = ApiResponse.responseList(StatusCode.SUCCESS, SuccessUserCode.USER_REGISTER_SUCCESS.getMessage(),list);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
