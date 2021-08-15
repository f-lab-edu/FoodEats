package com.flab.foodeats.menu.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flab.foodeats.menu.model.MenuEssentialParam;
import com.flab.foodeats.menu.model.MenuOptionParam;
import com.flab.foodeats.menu.model.MenuRegisterParam;

public interface MenuController {

	// 가맹점 메뉴 등록
	@PostMapping("/register-menu/{shopId}")
	public ResponseEntity<?> registerMenu(
		@PathVariable int shopId, @Valid @RequestBody MenuRegisterParam menuRegisterParam);

	// 가맹점별 메뉴 검색
	@GetMapping("/search-menu/{shopId}")
	public ResponseEntity<?> searchMenu(@PathVariable int shopId);

	// 메뉴별 옵션 검색
	@GetMapping("/search-menu/{shopId}/option/{menuId}")
	public ResponseEntity<?> searchMenuOption(@PathVariable("menuId") int menuId);

	// 메뉴 수정
	@PutMapping("/update-menu/{menuId}")
	public ResponseEntity<?> updateMenu(
		@PathVariable int menuId, @Valid @RequestBody MenuEssentialParam menuEssentialParam);

	// 메뉴 삭제
	@DeleteMapping("/delete-menu/{menuId}")
	public ResponseEntity<?> deleteMenu(@PathVariable int menuId);

	// 메뉴 옵션 수정
	@PutMapping("/update-menu-option/{menuOptionId}")
	public ResponseEntity<?> updateMenuOption(
		@PathVariable int menuOptionId, @Valid @RequestBody MenuOptionParam menuOptionParam);

	// 메뉴 옵션 삭제
	@DeleteMapping("/delete-menu-option/{menuOptionId}")
	public ResponseEntity<?> deleteMenuOption(@PathVariable int menuOptionId);
}
