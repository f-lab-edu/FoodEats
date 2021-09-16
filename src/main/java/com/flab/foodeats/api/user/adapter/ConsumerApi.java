package com.flab.foodeats.api.user.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.api.user.DeleteUserRequest;
import com.flab.foodeats.api.user.LoginUserRequest;
import com.flab.foodeats.api.user.ModifyUserRequest;
import com.flab.foodeats.api.user.RegisterUserRequest;
import com.flab.foodeats.application.user.port.UserService;
import com.flab.foodeats.common.response.ApiResponse;
import com.flab.foodeats.common.response.StatusCode;
import com.flab.foodeats.common.response.SuccessUserCode;
import com.flab.foodeats.common.util.SessionManager;
import com.flab.foodeats.domain.user.UserType;
import com.flab.foodeats.common.auth.AuthInfo;
import com.flab.foodeats.common.auth.AuthRequired;
import com.flab.foodeats.common.auth.AuthUsed;

@RestController
@RequestMapping("/consumer")
public class ConsumerApi {

	private final UserService userService;
	private final SessionManager sessionManager;

	public ConsumerApi(@Qualifier("consumerService") UserService userService, SessionManager sessionManager) {
		this.userService = userService;
		this.sessionManager = sessionManager;
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUserInfo(@Valid @RequestBody RegisterUserRequest dto) {
		userService.registerUserInfo(dto.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_REGISTER_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUserInfo(@Valid @RequestBody LoginUserRequest dto, HttpServletResponse response) {
		Long consumerId = userService.login(dto.toParam());
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_LOGIN_SUCCESS.getMessage());
		sessionManager.createSession(AuthInfo.consuemrOf(consumerId,dto.getUserId()), response);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@AuthRequired(role = UserType.CONSUMER)
	@PostMapping("/logout")
	public ResponseEntity<?> logoutConsumerUser(@AuthUsed AuthInfo authedLoginInfo, HttpServletRequest request) {
		sessionManager.expire(request);
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_LOGOUT_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@AuthRequired(role = UserType.CONSUMER)
	@PutMapping("/modification")
	public ResponseEntity<?> modifyUserInfo(@Valid @RequestBody ModifyUserRequest dto, @AuthUsed AuthInfo authInfo) {
		userService.modifyUserInfo(dto.toParam(authInfo));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_UPDATE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	@AuthRequired(role = UserType.CONSUMER)
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteConsumerUser(@Valid @RequestBody DeleteUserRequest dto, @AuthUsed AuthInfo authedLoginInfo) {
		userService.deleteUserInfo(dto.toParam(authedLoginInfo.getUserId()));
		ApiResponse apiResponse = ApiResponse.responseMessage(StatusCode.SUCCESS, SuccessUserCode.USER_DELETE_SUCCESS.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}

