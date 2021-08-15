package com.flab.foodeats.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.shop.model.ConvenientShopInfo;
import com.flab.foodeats.shop.model.EssentialShopInfo;
import com.flab.foodeats.shop.model.StatusShopInfo;

@Mapper
@Repository
public interface ShopMapper {
	// 가맹점 등록 (필수)
	void registerEssentialInfo(@Param("essentialShopInfo") EssentialShopInfo essentialShopInfo, Integer shopId);

	// 가맹점 수정 (필수)
	void updateEssentialInfo(@Param("essentialShopInfo") EssentialShopInfo essentialShopInfo, Integer shopId);

	// 가맹점 삭제 (필수)
	void deleteEssentialInfo(Integer shopId);

	// 가맹점 등록 (상태)
	void registerStatusInfo(@Param("statusShopInfo") StatusShopInfo statusShopInfo, Integer shopId);

	// 가맹점 수정 (상태)
	void updateStatusInfo(@Param("statusShopInfo") StatusShopInfo statusShopInfo, Integer shopId);

	// 가맹점 등록 (편리)
	void registerConvenienceInfo(@Param("convenientShopInfo") ConvenientShopInfo convenientShopInfo, Integer shopId);

	// 가맹점 수정 (편리)
	void updateConvenienceInfo(@Param("convenientShopInfo") ConvenientShopInfo convenientShopInfo, Integer shopId);

	// 가맹점 기본정보 조회 By ShopId
	Integer findEssentialInfoById(Integer shopId);

	// 가맹점 상태정보 조회 By ShopId/
	Integer findStatusInfoById(Integer shopId);

	// 가맹점 편리정보 조회 By ShopId
	Integer findConvenienceInfoById(Integer shopId);

	// 가맹점 기본정보 전체 조회
	List<EssentialShopInfo> ShopListAllInfo();

	// 가맹점 배달 가능 지역 등록
	void registerShopDeliveryLocation(@Param("shopId") Long shopId,
		@Param("deliveryLocation") String deliveryLocation,
		@Param("deliveryLocationTip") int deliveryLocationTip);
}
