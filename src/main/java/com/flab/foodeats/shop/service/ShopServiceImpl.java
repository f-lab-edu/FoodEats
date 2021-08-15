package com.flab.foodeats.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.shop.mapper.ShopMapper;
import com.flab.foodeats.shop.model.ConvenientShopInfo;
import com.flab.foodeats.shop.model.EssentialShopInfo;
import com.flab.foodeats.shop.model.ShopDeliveryLocation;
import com.flab.foodeats.shop.model.ShopDeliveryLocationParam;
import com.flab.foodeats.shop.model.StatusShopInfo;
import com.flab.foodeats.shop.model.code.SuccessShopCode;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.interceptor.auth.ShopAuth;

@Service
public class ShopServiceImpl implements ShopService {

	private ShopMapper shopMapper;
	private ShopErrorCheck shopErrorCheck;

	public ShopServiceImpl(ShopMapper shopMapper, ShopErrorCheck shopErrorCheck) {
		this.shopMapper = shopMapper;
		this.shopErrorCheck = shopErrorCheck;
	}

	// 가맹점 등록 (기본정보)
	public ApiResponse registerEssentialShopInfo(EssentialShopInfo essentialShopInfo, ShopAuth shopInfoStoredInSession) {
		shopErrorCheck.shopInfoAlreadyExist(shopMapper.findEssentialInfoByShopId(shopInfoStoredInSession.getShopId()));
		shopMapper.registerEssentialInfo(essentialShopInfo, shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_REGISTER_SUCCESS);
		return apiResponse;
	}

	// 가맹점 수정 (기본정보)
	public ApiResponse updateBasicShopInfo(EssentialShopInfo essentialShopInfo, ShopAuth shopInfoStoredInSession) {
		shopMapper.updateEssentialInfo(essentialShopInfo, shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_UPDATE_SUCCESS);
		return apiResponse;
	}

	// 가맹점 삭제 (기본정보)
	public ApiResponse deleteBasicShopInfo(ShopAuth shopInfoStoredInSession) {
		/**
		 * 가맹점 기본정보 삭제시 모든 테이블 삭제
		 */
		shopMapper.deleteEssentialInfo(shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_DELETE_SUCCESS);
		return apiResponse;
	}

	// 가맹점 등록 (상태정보)
	public ApiResponse registerDetailShopInfo(StatusShopInfo statusShopInfo, ShopAuth shopInfoStoredInSession) {
		shopErrorCheck.shopInfoAlreadyExist(shopMapper.findStatusInfoByShopId(shopInfoStoredInSession.getShopId()));
		shopErrorCheck.shopEssentialInfoNotExist(shopMapper.findEssentialInfoByShopId(shopInfoStoredInSession.getShopId()));
		shopMapper.registerStatusInfo(statusShopInfo, shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_REGISTER_SUCCESS);
		return apiResponse;
	}

	// 가맹점 수정 (상태정보)
	public ApiResponse updateDetailShopInfo(StatusShopInfo statusShopInfo, ShopAuth shopInfoStoredInSession) {
		shopMapper.updateStatusInfo(statusShopInfo, shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_UPDATE_SUCCESS);
		return apiResponse;
	}

	// 가맹점 등록 (편리정보)
	public ApiResponse registerConvenienceShopInfo(ConvenientShopInfo convenientShopInfo, ShopAuth shopInfoStoredInSession) {
		shopErrorCheck.shopInfoAlreadyExist(shopMapper.findConvenienceInfoByShopId(shopInfoStoredInSession.getShopId()));
		shopErrorCheck.shopEssentialInfoNotExist(shopMapper.findEssentialInfoByShopId(shopInfoStoredInSession.getShopId()));
		shopMapper.registerConvenienceInfo(convenientShopInfo, shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_REGISTER_SUCCESS);
		return apiResponse;
	}

	// 가맹점 수정 (편리정보)
	public ApiResponse updateConvenienceShopInfo(ConvenientShopInfo convenientShopInfo, ShopAuth shopInfoStoredInSession) {
		shopMapper.updateConvenienceInfo(convenientShopInfo, shopInfoStoredInSession.getShopId());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_UPDATE_SUCCESS);
		return apiResponse;
	}



	/**
	 * 추후 조회는 삭제
	 * 유석햄 코드에 ChangeShopStatusAutomatic 적용
	 */
	// 가맹점 기본정보 전체 조회
	public ApiResponse searchShopAllInfo() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, shopMapper.shopListAllInfo());
		return apiResponse;
	}

	// 가맹점 배달 가능 지역 등록
	@Override
	public ApiResponse registerShopDeliveryLocation(Long shopId, ShopDeliveryLocation shopDeliveryLocation) {
		List<ShopDeliveryLocationParam> deliveryParams = shopDeliveryLocation.getDeliveryParams();
		for (ShopDeliveryLocationParam deliveryParam : deliveryParams) {
			shopMapper.registerShopDeliveryLocation(shopId, deliveryParam.getDeliveryLocation(),
				deliveryParam.getDeliveryLocationTip());
		}
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessShopCode.SHOP_UPDATE_SUCCESS);
		return apiResponse;
	}
}
