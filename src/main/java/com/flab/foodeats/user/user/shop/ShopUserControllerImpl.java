package com.flab.foodeats.user.user.shop;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.user.interceptor.auth.AuthRequired;
import com.flab.foodeats.global.ApiResponse;
import com.flab.foodeats.user.interceptor.auth.AuthSessionControl;
import com.flab.foodeats.user.interceptor.auth.ShopAuthSessionControl;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.interceptor.auth.ShopAuth;
import com.flab.foodeats.user.model.UpdateFormDTO;


@RestController
@RequestMapping("/user/shop")
public class ShopUserControllerImpl implements ShopUserController {

	private ShopUserService shopUserService;

	public ShopUserControllerImpl(ShopUserService shopUserService) {
		this.shopUserService = shopUserService;
	}

	/**
	 * 필터 / 인터셉터 적용 x
	 */
	// 가맹점 - 회원가입
	@PostMapping("/register")
	public ResponseEntity<?> registerShopUser(InsertFormDTO insertFormDTO) {
		ApiResponse apiResponse = shopUserService.registerShopUser(insertFormDTO);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 - 로그인
	@PostMapping("/login")
	public ResponseEntity<?> loginShopUser(LoginFormDTO loginFormDTO, ShopAuth shopAuth, HttpSession httpSession) {
		ApiResponse apiResponse = shopUserService.loginShopUser(loginFormDTO, shopAuth);
		httpSession.setAttribute(ShopAuth.SHOP_KEY, shopAuth);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 - 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logoutShopUser(HttpSession httpSession) {
		ApiResponse apiResponse = shopUserService.logoutShopUser();
		httpSession.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}


	/**
	 * 필터 / 입터셉터 적용 o
	 * @AuthPreHandler 어노테이션 기반 인터셉터
	 */
	// 가맹점 - 회원 수정
	@AuthRequired
	@PutMapping("/update")
	public ResponseEntity<?> updateShopUser(UpdateFormDTO updateFormDTO) {
		ApiResponse apiResponse = shopUserService.updateShopUser(updateFormDTO, AuthSessionControl.getShopAuthentication().getId());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

	// 가맹점 - 회원 삭제
	@AuthRequired
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteShopUser() {
		ApiResponse apiResponse = shopUserService.deleteShopUser(AuthSessionControl.getShopAuthentication().getId());
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}
}
