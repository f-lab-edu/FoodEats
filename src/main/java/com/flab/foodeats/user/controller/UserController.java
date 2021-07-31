package com.flab.foodeats.user.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flab.foodeats.user.model.DeleteFormDTO;
import com.flab.foodeats.user.model.InsertFormDTO;
import com.flab.foodeats.user.model.LoginFormDTO;
import com.flab.foodeats.user.model.UpdateFormDTO;

public interface UserController {

	@PostMapping("/insert")
	public ResponseEntity<?> insertUser(@Valid @RequestBody InsertFormDTO insertFormDTO);

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginFormDTO loginFormDTO, HttpSession httpSession);

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession httpSession);

	@GetMapping("/all")
	public ResponseEntity<?> findAll();

	@GetMapping("/findInfo")
	public ResponseEntity<?> findInfoById(@Valid @RequestBody LoginFormDTO loginFormDTO);

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UpdateFormDTO updateFormDTO);

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteFormDTO deleteFormDTO);
}
