package com.flab.foodeats.user.user.shop;

import org.springframework.stereotype.Service;

import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.global.StatusCode;
import com.flab.foodeats.user.mapper.UserMapper;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;
import com.flab.foodeats.user.model.code.SuccessUserCode;
import com.flab.foodeats.user.service.InsertErrorCheck;
import com.flab.foodeats.user.service.LoginErrorCheck;
import com.flab.foodeats.user.service.UserInfoEncoder;

@Service
public class ShopUserServiceImpl implements ShopUserService {

	private UserMapper userMapper;
	private InsertErrorCheck insertErrorCheck;
	private LoginErrorCheck loginErrorCheck;

	public ShopUserServiceImpl(UserMapper userMapper, InsertErrorCheck insertErrorCheck,
		LoginErrorCheck loginErrorCheck) {
		this.userMapper = userMapper;
		this.insertErrorCheck = insertErrorCheck;
		this.loginErrorCheck = loginErrorCheck;
	}

	// 가맹점 - 회원가입
	public ApiResponse registerShopUser(InsertFormDTO insertFormDTO) {
		insertErrorCheck.idAlreadyExistCheck(userMapper.findShopUserById(insertFormDTO.getId()));
		insertFormDTO.setPassword(new UserInfoEncoder().encodePassword(insertFormDTO.getPassword()));
		userMapper.registerShopUser(insertFormDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_INSERT_SUCCESS);
		return apiResponse;
	}


	// 가맹점 - 로그인
	@Override
	public ApiResponse loginShopUser(LoginFormDTO loginFormDTO) {
		loginErrorCheck.notExistUserValid(userMapper.findShopUserPassword(loginFormDTO.getId()));
		loginErrorCheck.validationLogin(userMapper.findShopUserPassword(loginFormDTO.getId()), loginFormDTO.getPassword());
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_LOGIN_SUCCESS);
		return apiResponse;
	}

	// 가맹점 - 로그아웃
	@Override
	public ApiResponse logoutShopUser() {
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_LOGOUT_SUCCESS);
		return apiResponse;
	}


	// 가맹점 - 회원 수정
	@Override
	public ApiResponse updateShopUser(UpdateFormDTO updateFormDTO, String authenticationInfo) {
		updateFormDTO.setPassword(new UserInfoEncoder().encodePassword(updateFormDTO.getPassword()));
		userMapper.updateShopUserInfo(authenticationInfo, updateFormDTO);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_UPDATE_SUCCESS);
		return apiResponse;
	}

	// 가맹점 - 회원 삭제
	@Override
	public ApiResponse deleteShopUser(String authenticationInfo) {
		userMapper.deleteShopUserInfo(authenticationInfo);
		ApiResponse apiResponse = new ApiResponse(StatusCode.SUCCESS, SuccessUserCode.USER_DELETE_SUCCESS);
		return apiResponse;
	}

}
