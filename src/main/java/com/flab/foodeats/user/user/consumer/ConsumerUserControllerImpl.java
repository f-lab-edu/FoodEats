package com.flab.foodeats.user.user.consumer;

import com.flab.foodeats.user.interceptor.auth.Auth;
import com.flab.foodeats.user.interceptor.auth.AuthRequired;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.model.UpdateFormDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user/consumer")
public class ConsumerUserControllerImpl implements ConsumerUserController {

	private ConsumerUserService consumerUserService;

	public ConsumerUserControllerImpl(ConsumerUserService consumerUserService) {
		this.consumerUserService = consumerUserService;
	}

	/**
	 * 필터 / 인터셉터 적용 x (로그인 인증)
	 */

	// 소비자 - 회원가입
	@PostMapping("/register")
	public ResponseEntity<?> registerConsumerUser(InsertFormDTO insertFormDTO) {
		ApiResponse apiResponse = consumerUserService.registerConsumerUser(insertFormDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}


	// 소비자 - 로그인
	@PostMapping("/login")
	public ResponseEntity<?> loginConsumerUser(LoginFormDTO loginFormDTO, HttpSession httpSession) {
		ApiResponse apiResponse = consumerUserService.loginConsumerUser(loginFormDTO);
		httpSession.setAttribute(Auth.CUNSUMER_KEY, loginFormDTO.getId());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 소비자 - 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logoutConsumerUser(HttpSession httpSession) {
		ApiResponse apiResponse = consumerUserService.logoutConsumerUser();
		httpSession.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	/**
	 * 필터 / 입터셉터 적용 o (로그인 인증)
	 * @AuthPreHandler 어노테이션 기반 인터셉터
	 */

	// 소비자 - 회원 수정
	@AuthRequired
	@PutMapping("/update")
	public ResponseEntity<?> updateConsumerUser(UpdateFormDTO updateFormDTO) {
		ApiResponse apiResponse = consumerUserService.updateConsumerUser(updateFormDTO,AuthSessionControl.getAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 소비자 - 회원 삭제
	@AuthRequired
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteConsumerUser() {
		ApiResponse apiResponse = consumerUserService.deleteConsumerUser(AuthSessionControl.getAuthentication());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
