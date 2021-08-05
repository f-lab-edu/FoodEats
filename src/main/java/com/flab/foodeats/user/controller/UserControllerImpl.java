package com.flab.foodeats.user.controller;

import com.flab.foodeats.user.interceptor.auth.Auth;
import com.flab.foodeats.user.interceptor.auth.AuthPreHandler;
import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.ApiResponse;
import com.flab.foodeats.user.model.UpdateFormDTO;
import com.flab.foodeats.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

	private UserService userService;

	public UserControllerImpl(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 필터 / 인터셉터 적용 x
	 * 회원가입
	 * 로그인
	 * 로그아웃
	 * 전체 사용자 조회
	 */

	// 회원가입
	@PostMapping("/insert")
	public ResponseEntity<?> insertUser(@Valid @RequestBody InsertFormDTO insertFormDTO) {
		ApiResponse apiResponse = userService.insertUserInfo(insertFormDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginFormDTO loginFormDTO, HttpSession httpSession) {
		ApiResponse apiResponse = userService.login(loginFormDTO);
		httpSession.setAttribute(Auth.KEY, loginFormDTO.getId());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession httpSession) {
		ApiResponse apiResponse = userService.logout();
		httpSession.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 전체 조회
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		ApiResponse apiResponse = userService.findAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 단일 회원 조회
	@GetMapping("/findInfo")
	public ResponseEntity<?> findInfoById(@Valid @RequestBody LoginFormDTO loginFormDTO) {
		ApiResponse apiResponse = userService.findInfoById(loginFormDTO.getId());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	/**
	 * 필터 / 입터셉터 적용 o
	 * 회원수정
	 * 회원탈퇴
	 */

	// 회원 수정
	@AuthPreHandler
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UpdateFormDTO updateFormDTO) {
		ApiResponse apiResponse = userService.updateUserInfo(updateFormDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 회원 삭제
	@AuthPreHandler
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteFormDTO deleteFormDTO) {
		ApiResponse apiResponse = userService.deleteUserInfo(deleteFormDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
