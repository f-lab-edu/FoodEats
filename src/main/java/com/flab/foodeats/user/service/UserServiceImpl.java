package com.flab.foodeats.user.service;

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

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	private LoginErrorCheck loginErrorCheck;
	private InsertErrorCheck insertErrorCheck;

	public UserServiceImpl(UserMapper userMapper,
		LoginErrorCheck loginErrorCheck,
		InsertErrorCheck insertErrorCheck) {
		this.userMapper = userMapper;
		this.loginErrorCheck = loginErrorCheck;
		this.insertErrorCheck = insertErrorCheck;
	}

	// 회원가입
	@Override
	public ApiResponse insertUserInfo(InsertFormDTO insertFormDTO) {
		insertErrorCheck.Insert(userMapper.findMemberById(insertFormDTO.getId()));
		userMapper.save(insertFormDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_INSERT_SUCCESS);
		return apiResponse;
	}

	// 로그인
	@Override
	public ApiResponse login(LoginFormDTO loginFormDTO) {
		loginErrorCheck.UserNotExist(userMapper.findPassword(loginFormDTO.getId()));
		loginErrorCheck.PasswordUnMatch(userMapper.findPassword(loginFormDTO.getId()), loginFormDTO.getPassword());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_LOGIN_SUCCESS);
		return apiResponse;
	}

	// 로그아웃
	@Override
	public ApiResponse logout() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_LOGOUT_SUCCESS);
		return apiResponse;
	}

	// 전체 회원 조회
	@Override
	public ApiResponse findAllUser() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, userMapper.findAll());
		return apiResponse;
	}

	// 단일 회원 조회
	@Override
	public ApiResponse findInfoById(String id) {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, userMapper.findMemberById(id));
		return apiResponse;
	}

	// 회원 수정
	@Override
	public ApiResponse updateUserInfo(UpdateFormDTO updateFormDTO) {
		userMapper.updateInfo(AuthSessionControl.getAuthentication(), updateFormDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_UPDATE_SUCCESS);
		return apiResponse;
	}

	// 회원 삭제
	@Override
	public ApiResponse deleteUserInfo(DeleteFormDTO deleteFormDTO) {
		userMapper.deleteUserInfo(AuthSessionControl.getAuthentication());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_DELETE_SUCCESS);
		return apiResponse;
	}
}
