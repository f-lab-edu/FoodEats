package com.flab.foodeats.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flab.foodeats.common.Encryption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Table(name = "user")
@Entity
@ToString
public class User {

	@Id
	@Column(name = "user_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "user_passowrd", nullable = false)
	private String password;

	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "user_email", nullable = false)
	private String email;

	@Column(name = "user_phone", nullable = false)
	private String phone;

	@Column(name = "user_address", nullable = false)
	private String address;

	public User() {
	}

	public User(String userId, String password) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
	}

	@Builder
	public User(Long id, String userId, String password, String name, String email, String phone, String address) {
		this.id = id;
		this.userId = userId;
		this.password = Encryption.encoder(password);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


}
