package com.flab.foodeats.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flab.foodeats.common.Encryption;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Table(name = "consumer_user")
@Entity
@ToString
public class Consumer {

	@Id
	@Column(name = "consumer_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "consumer_id", nullable = false)
	private String userId;

	@Column(name = "consumer_passowrd", nullable = false)
	private String password;

	@Column(name = "consumer_name", nullable = false)
	private String name;

	@Column(name = "consumer_email", nullable = false)
	private String email;

	@Column(name = "consumer_phone", nullable = false)
	private String phone;

	@Column(name = "consumer_address", nullable = false)
	private String address;

	public Consumer() {
	}

	public Consumer(String userId, String password) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
	}

	@Builder
	public Consumer(Long id, String userId, String password, String name, String email, String phone, String address) {
		this.id = id;
		this.userId = userId;
		this.password = Encryption.encoder(password);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


}
