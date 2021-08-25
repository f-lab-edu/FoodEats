package com.flab.foodeats.infra.shop;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.domain.shop.Essential;

@Mapper
@Repository
public interface ShopMapper {
	// 가맹점 등록 (필수)
	void registerEssentialInfo(@Param("Essential") Essential essential);
	// 가맹점 수정 (필수)
	void updateEssentialInfo(@Param("Essential") Essential essential);
	// 가맹점 삭제 (필수)
	void deleteEssentialInfo(String shopId);

	// 가맹점 기본정보 조회 - 중복등록 검증
	Essential findEssentialByShopId(String shopId);


}

