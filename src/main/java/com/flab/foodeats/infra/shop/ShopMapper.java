package com.flab.foodeats.infra.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.shop.ShopDelivery;
import com.flab.foodeats.domain.shop.Shop;
import com.flab.foodeats.domain.shop.BusinessHour;

@Mapper
@Repository
public interface ShopMapper {
	// 가맹점 등록 (필수)
	void registerShopInfo(@Param("Shop") Shop shop);
	// 가맹점 수정 (필수)
	void updateShopInfo(@Param("Shop") Shop shop);
	// 가맹점 삭제 (필수)
	void deleteShopInfo(Long shopId);


	// 가맹점 등록 (상태)
	void registerBusinessHour(@Param("BusinessHour") BusinessHour businessHour);
	// 가맹점 수정 (상태)
	void updateBusinessHour(@Param("BusinessHour") BusinessHour businessHour);
	// 가맹점 삭제 (상태)
	void deleteBusinessHour(Long shopId);

	// 가맹점 등록 (편리)
	void registerShopDeliveryInfo(@Param("ShopDelivery") ShopDelivery shopDelivery);
	// 가맹점 수정 (편리)
	void updateShopDeliveryInfo(@Param("ShopDelivery") ShopDelivery shopDelivery);
	// 가맹점 삭제 (편리)
	void deleteShopDeliveryInfo(Long shopId);

	// 가맹점 기본정보 조회 - 중복등록 검증
	Shop findShopInfoByShopId(Long shopId);
	// 가맹점 상태정보 조회 - 중복등록 검증
	BusinessHour findBusinessHourByShopId(Long shopId);
	// 가맹점 편리정보 조회 - 중복등록 검증
	ShopDelivery findShopDeliveryInfoByShopId(Long shopId);


	// 가맹점 기본정보 전체 조회
	List<Shop> shopListAllInfo();
	// 가게 상태 오픈
	void startShop(@Param("BusinessHour") BusinessHour businessHour);
	// 가게 상태 마감
	void closeShop(@Param("BusinessHour") BusinessHour businessHour);


}

