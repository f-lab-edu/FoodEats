package com.flab.foodeats.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.foodeats.shop.model.ShopDeleteDTO;
import com.flab.foodeats.shop.model.ShopInfoListByIdDTO;
import com.flab.foodeats.shop.model.ShopOpenStatusDTO;
import com.flab.foodeats.shop.model.ShopRegistrationDTO;
import com.flab.foodeats.shop.model.ShopUpdateDTO;

@Mapper
@Repository
public interface ShopMapper {
	// 회원가입
	void registrationShop(@Param("shopRegistrationDTO") ShopRegistrationDTO shopRegistrationDTO);

	// 회원 수정
	void updateShop(@Param("shopUpdateDTO") ShopUpdateDTO shopUpdateDTO);

	// 회원 수정
	void deleteShop(@Param("shopDeleteDTO") ShopDeleteDTO shopDeleteDTO);

	// 단일 회원 조회
	ShopRegistrationDTO findShopById(int shopId);

	// 전회 조회
	List<ShopInfoListByIdDTO> ShopListAllInfo();

	List<ShopInfoListByIdDTO> ShopListOneInfo(String shopId);

	void startShop(@Param("shopOpenStatusDTO") ShopOpenStatusDTO shopOpenStatusDTO);

	void closeShop(@Param("shopOpenStatusDTO") ShopOpenStatusDTO shopOpenStatusDTO);


	/*// 로그인
	String findPassword(String id);

	// 모든 회원 조회
	List<InsertFormDTO> findAll();

	// 단일 회원 조회
	InsertFormDTO findMemberById(String id);

	// 회원 수정
	void updateInfo(@Param("id") String id, @Param("updateFormDTO") UpdateFormDTO updateFormDTO);

	// 회원 탈퇴
	void deleteUserInfo(String id);*/
}
