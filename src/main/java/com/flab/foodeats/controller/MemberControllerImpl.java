package com.flab.foodeats.controller;

import static com.flab.foodeats.model.ResponseStatus.*;

import com.flab.foodeats.SessionConst;
import com.flab.foodeats.interceptor.UserLoginAuth;
import com.flab.foodeats.model.DeleteFormDTO;
import com.flab.foodeats.model.LoginFormDTO;
import com.flab.foodeats.model.InsertFormDTO;
import com.flab.foodeats.model.ResponseResult;
import com.flab.foodeats.model.UpdateFormDTO;
import com.flab.foodeats.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 로그인 기반 코드
 * @author yusok
 */

@RestController
@RequestMapping("/user")
public class MemberControllerImpl implements MemberController {

	private UserService userService;

	public MemberControllerImpl(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 필터 / 인터셉터 적용 x
	 * 회원가입
	 * 로그인
	 * 로그아웃
	 * 전체 사용자 조회
	 */

	public ResponseEntity<?> insertUser(@Valid @RequestBody InsertFormDTO insertFormDTO, BindingResult bindingResult) {
		inputValid(bindingResult);
		ResponseResult responseResult = new ResponseResult(SUCCESS, userService.insertUserInfo(insertFormDTO));
		return new ResponseEntity<>(responseResult, HttpStatus.CREATED);
	}

	// 로그인
	public ResponseEntity<?> login(@Valid @RequestBody LoginFormDTO loginFormDTO, BindingResult bindingResult,
		HttpSession httpSession) {
		inputValid(bindingResult);
		ResponseResult responseResult = new ResponseResult(SUCCESS, userService.login(loginFormDTO));
		httpSession.setAttribute(SessionConst.LOGIN, loginFormDTO);
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}

	// 로그아웃
	public ResponseEntity<Void> logout(HttpSession httpSession) {
		httpSession.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 전체 조회
	public ResponseEntity<?> findAll() {
		ResponseResult responseResult = new ResponseResult(SUCCESS, userService.findAllUser());
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}

	/**
	 * 필터 / 입터셉터 적용 메소드
	 * 회원수정 (update)
	 * 회원탈퇴 (delete)
	 */

	// 회원정보 수정
	@UserLoginAuth
	public ResponseEntity<?> update(@Valid @RequestBody UpdateFormDTO updateFormDTO, BindingResult bindingResult,
		HttpSession httpSession) {
		inputValid(bindingResult);
		LoginFormDTO sessionMember = (LoginFormDTO)httpSession.getAttribute(SessionConst.LOGIN);
		ResponseResult responseResult = new ResponseResult(SUCCESS,
			userService.updateUserInfo(sessionMember, updateFormDTO));
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}

	// 회원 삭제
	@UserLoginAuth
	public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteFormDTO deleteFormDTO, BindingResult bindingResult,
		HttpSession httpSession) {
		inputValid(bindingResult);
		LoginFormDTO sessionMember = (LoginFormDTO)httpSession.getAttribute(SessionConst.LOGIN);
		ResponseResult responseResult = new ResponseResult(SUCCESS,
			userService.deleteUserInfo(sessionMember, deleteFormDTO));
		httpSession.invalidate();
		return new ResponseEntity<>(responseResult, HttpStatus.OK);
	}

	private void inputValid(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("잘못된 입력 값");
		}
	}

}
