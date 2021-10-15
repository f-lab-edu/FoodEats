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
@Table(name = "rider_user")
@Entity
@ToString
public class Rider {

	@Id
	@Column(name = "rider_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rider_id", nullable = false)
	private String userId;

	@Column(name = "rider_passowrd", nullable = false)
	private String password;

	@Column(name = "rider_name", nullable = false)
	private String name;

	@Column(name = "rider_email", nullable = false)
	private String email;

	@Column(name = "rider_phone", nullable = false)
	private String phone;

	@Column(name = "rider_address", nullable = false)
	private String address;

	public Rider() {
	}

	public Rider(String userId, String password) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
	}

	@Builder
	public Rider(Long id, String userId, String password, String name, String email, String phone, String address) {
		this.id = id;
		this.userId = userId;
		this.password = Encryption.encoder(password);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


}
