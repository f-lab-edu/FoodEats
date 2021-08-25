package com.flab.foodeats.api.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.flab.foodeats.application.user.ModifyUserTarget;

public class ModifyUserRequest {

	@NotBlank(message = "Input Your Password")
	private String password;

	@NotBlank(message = "Input Your Name")
	private String name;

	@NotBlank(message = "Input Your Email")
	@Email(message = "Please follow the email format and enter it.")
	private String email;

	@NotBlank(message = "Input Your Phone")
	@Pattern(regexp = "[0-9]{10,11}", message = "Enter a 10- or 11-digit phone number (excluding -)")
	private String phone;

	@NotBlank(message = " Input Your Address")
	private String address;

	public ModifyUserRequest() {
	}

	public ModifyUserRequest(String password, String name, String email, String phone, String address) {
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public ModifyUserTarget toParam(String userId) {
		return new ModifyUserTarget(userId, password, name, email, phone, address);
	}
}