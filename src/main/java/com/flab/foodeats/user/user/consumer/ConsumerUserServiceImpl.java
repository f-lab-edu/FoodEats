package com.flab.foodeats.user.user.consumer;

import org.springframework.stereotype.Service;

import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.mapper.UserMapper;
import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.user.model.UpdateFormDTO;
import com.flab.foodeats.user.model.code.SuccessUserCode;
import com.flab.foodeats.user.service.InsertErrorCheck;
import com.flab.foodeats.user.service.LoginErrorCheck;
import com.flab.foodeats.user.service.UserPasswordEncoder;
import com.flab.foodeats.user.user.consumer.ConsumerUserService;

@Service
public class ConsumerUserServiceImpl implements ConsumerUserService {

	private UserMapper userMapper;
	private InsertErrorCheck insertErrorCheck;
	private LoginErrorCheck loginErrorCheck;
	private UserPasswordEncoder userPasswordEncoder;

	public ConsumerUserServiceImpl(UserMapper userMapper, InsertErrorCheck insertErrorCheck,
		LoginErrorCheck loginErrorCheck, UserPasswordEncoder userPasswordEncoder) {
		this.userMapper = userMapper;
		this.insertErrorCheck = insertErrorCheck;
		this.loginErrorCheck = loginErrorCheck;
		this.userPasswordEncoder = userPasswordEncoder;
	}

	// 소비자 - 회원가입
	@Override
	public ApiResponse registerConsumerUser(InsertFormDTO insertFormDTO) {
		insertErrorCheck.idAlreadyExistCheck(userMapper.findConsumerUserById(insertFormDTO.getId()));
		userPasswordEncoder.loginEncoder(insertFormDTO);
		userMapper.registerConsumerUser(insertFormDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_INSERT_SUCCESS);
		return apiResponse;
	}

	// 소비자 - 로그인
	@Override
	public ApiResponse loginConsumerUser(LoginFormDTO loginFormDTO) {
		loginErrorCheck.UserNotExist(userMapper.findConsumerUserPassword(loginFormDTO.getId()));
		loginErrorCheck.PasswordUnMatch(userMapper.findConsumerUserPassword(loginFormDTO.getId()), loginFormDTO.getPassword());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_LOGIN_SUCCESS);
		return apiResponse;
	}

	// 소비자 - 로그아웃
	@Override
	public ApiResponse logoutConsumerUser() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_LOGOUT_SUCCESS);
		return apiResponse;
	}

	// 소비자 - 회원 수정
	@Override
	public ApiResponse updateConsumerUser(UpdateFormDTO updateFormDTO, String authenticationInfo) {
		userPasswordEncoder.updateEncoder(updateFormDTO);
		userMapper.updateConsumerUserInfo(authenticationInfo, updateFormDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_UPDATE_SUCCESS);
		return apiResponse;
	}

	// 소비자 - 회원 삭제
	@Override
	public ApiResponse deleteConsumerUser(String authenticationInfo) {
		userMapper.deleteConsumerUserInfo(authenticationInfo);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_DELETE_SUCCESS);
		return apiResponse;
	}

}
