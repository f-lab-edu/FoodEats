package com.flab.foodeats.user.user.consumer;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.model.UpdateFormDTO;

@Service
public interface ConsumerUserService {
	// 소비자 - 회원가입
	ApiResponse registerConsumerUser(InsertFormDTO insertFormDTO);
	// 소비자 - 로그인
	ApiResponse loginConsumerUser(LoginFormDTO loginFormDTO);
	// 소비자 - 로그아웃
	ApiResponse logoutConsumerUser();
	// 소비자 - 회원수정
	ApiResponse updateConsumerUser(UpdateFormDTO updateFormDTO, String authenticationInfo);
	// 소비자 - 로그아웃
	ApiResponse deleteConsumerUser(String authenticationInfo);
}
