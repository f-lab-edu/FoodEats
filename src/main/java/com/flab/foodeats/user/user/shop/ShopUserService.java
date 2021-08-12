package com.flab.foodeats.user.user.shop;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

@Service
public interface ShopUserService {
	// 가맹점 - 회원가입
	ApiResponse registerShopUser(InsertFormDTO insertFormDTO);
	// 가맹점 - 로그인
	ApiResponse loginShopUser(LoginFormDTO loginFormDTO);
    // 가맹점 - 로그아웃
	ApiResponse logoutShopUser();
    // 가맹점 - 회원수정
	ApiResponse updateShopUser(UpdateFormDTO updateFormDTO, String authenticationInfo);
	// 가맹점 - 회원삭제
	ApiResponse deleteShopUser(String authenticationInfo);
}
