package com.flab.foodeats.infra.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.api.shop.EssentialRequest;
import com.flab.foodeats.domain.shop.Convenient;
import com.flab.foodeats.domain.shop.Essential;
import com.flab.foodeats.domain.shop.Status;

@Mapper
@Repository
public interface ShopMapper {
	// 가맹점 등록 (필수)
	void registerEssentialInfo(@Param("Essential") Essential essential);
	// 가맹점 수정 (필수)
	void updateEssentialInfo(@Param("Essential") Essential essential);
	// 가맹점 삭제 (필수)
	void deleteEssentialInfo(Long shopId);


	// 가맹점 등록 (상태)
	void registerStatusInfo(@Param("Status") Status status);
	// 가맹점 수정 (상태)
	void updateStatusInfo(@Param("Status") Status status);
	// 가맹점 삭제 (상태)
	void deleteStatusInfo(Long shopId);

	// 가맹점 등록 (편리)
	void registerConvenienceInfo(@Param("Convenient") Convenient convenient);
	// 가맹점 수정 (편리)
	void updateConvenienceInfo(@Param("Convenient") Convenient convenient);
	// 가맹점 삭제 (편리)
	void deleteConvenienceInfo(Long shopId);

	// 가맹점 기본정보 조회 - 중복등록 검증
	Essential findEssentialByShopId(String userId);
	// 가맹점 상태정보 조회 - 중복등록 검증
	Status findStatusByShopId(String userId);
	// 가맹점 편리정보 조회 - 중복등록 검증
	Convenient findConvenienceByShopId(String userId);


	// 가맹점 기본정보 전체 조회
	List<Essential> shopListAllInfo();
	// 가게 상태 오픈
	void startShop(@Param("Status") Status status);
	// 가게 상태 마감
	void closeShop(@Param("Status") Status status);

	int findShopIdByMerchantId(String userId);
}

