package com.flab.foodeats.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flab.foodeats.interceptor.UserLoginAuth;
import com.flab.foodeats.model.DeleteFormDTO;
import com.flab.foodeats.model.InsertFormDTO;
import com.flab.foodeats.model.LoginFormDTO;
import com.flab.foodeats.model.UpdateFormDTO;

public interface MemberController {

	@PostMapping("/insert")
	public ResponseEntity<?> insertUser(@Valid @RequestBody InsertFormDTO insertFormDTO, BindingResult bindingResult);

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginFormDTO loginFormDTO, BindingResult bindingResult,
		HttpSession httpSession);

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession httpSession);

	@GetMapping("/all")
	public ResponseEntity<?> findAll();

	@UserLoginAuth
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UpdateFormDTO updateFormDTO, BindingResult bindingResult,
		HttpSession httpSession);

	@UserLoginAuth
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteFormDTO deleteFormDTO, BindingResult bindingResult,
		HttpSession httpSession);
}
