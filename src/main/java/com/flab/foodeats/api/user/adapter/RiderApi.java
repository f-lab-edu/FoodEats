package com.flab.foodeats.api.user.adapter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.user.LoginUserRequest;
import com.flab.foodeats.api.user.ModifyUserRequest;
import com.flab.foodeats.api.user.RegisterUserRequest;
import com.flab.foodeats.application.user.port.UserService;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.domain.user.UserType;
import com.flab.foodeats.domain.user.auth.AuthInfo;
import com.flab.foodeats.domain.user.auth.AuthRequired;
import com.flab.foodeats.domain.user.auth.AuthUsed;

@RestController
@RequestMapping("/rider")
public class RiderApi {

	private UserService userService;

	public RiderApi(@Qualifier("riderService") UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUserInfo(@Valid @RequestBody RegisterUserRequest dto) {
		userService.registerUserInfo(dto.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_REGISTER_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUserInfo(@Valid @RequestBody LoginUserRequest dto, HttpSession httpSession) {
		userService.loginUserInfo(dto.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_LOGIN_SUCCESS);
		httpSession.setAttribute(AuthInfo.AUTH_KEY, AuthInfo.riderOf(dto.getUserId()));
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@AuthRequired(role = UserType.RIDER)
	@PutMapping("/modification")
	public ResponseEntity<?> modifyUserInfo(@Valid @RequestBody ModifyUserRequest dto, @AuthUsed AuthInfo authInfo) {
		userService.modifyUserInfo(dto.toParam(authInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_UPDATE_SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
