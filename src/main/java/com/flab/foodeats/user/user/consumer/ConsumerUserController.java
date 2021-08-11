package com.flab.foodeats.user.user.consumer;

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
@RequestMapping("/user/consumer")
public interface ConsumerUserController {

	@PostMapping("/register")
	public ResponseEntity<?> registerConsumerUser(@Valid @RequestBody InsertFormDTO insertFormDTO);

	@PostMapping("/login")
	public ResponseEntity<?> loginConsumerUser(@Valid @RequestBody LoginFormDTO loginFormDTO, HttpSession httpSession);

	@PostMapping("/logout")
	public ResponseEntity<?> logoutConsumerUser(HttpSession httpSession);

	@PutMapping("/update")
	public ResponseEntity<?> updateConsumerUser(@Valid @RequestBody UpdateFormDTO updateFormDTO);

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteConsumerUser();
}
