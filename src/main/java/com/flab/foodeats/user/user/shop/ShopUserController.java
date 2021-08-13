package com.flab.foodeats.user.user.shop;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

@RestController
@RequestMapping("/user/shop")
public interface ShopUserController {

	@PostMapping("/register")
	public ResponseEntity<?> registerShopUser(@Valid @RequestBody InsertFormDTO insertFormDTO);

	@PostMapping("/login")
	public ResponseEntity<?> loginShopUser(@Valid @RequestBody LoginFormDTO loginFormDTO, HttpSession httpSession);

	@PostMapping("/logout")
	public ResponseEntity<?> logoutShopUser(HttpSession httpSession);

	@PutMapping("/update")
	public ResponseEntity<?> updateShopUser(@Valid @RequestBody UpdateFormDTO updateFormDTO);

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteShopUser();
}
