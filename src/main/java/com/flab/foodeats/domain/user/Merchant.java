package com.flab.foodeats.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.flab.foodeats.common.Encryption;
import com.flab.foodeats.domain.shop.Shop;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Table(name = "merchant_user")
@Entity
@ToString
public class Merchant {

	@Id
	@Column(name = "merchant_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "merchant_id", nullable = false)
	private String userId;

	@Column(name = "merchant_passowrd", nullable = false)
	private String password;

	@Column(name = "merchant_name", nullable = false)
	private String name;

	@Column(name = "merchant_email", nullable = false)
	private String email;

	@Column(name = "merchant_phone", nullable = false)
	private String phone;

	@Column(name = "merchant_address", nullable = false)
	private String address;

	// @OneToMany(mappedBy = "merchant")
	// private List<Shop> shopInfo = new ArrayList<>();

	public Merchant() {
	}

	public Merchant(String userId, String password) {
		this.userId = userId;
		this.password = Encryption.encoder(password);
	}

	@Builder
	public Merchant(Long id, String userId, String password, String name, String email, String phone, String address) {
		this.id = id;
		this.userId = userId;
		this.password = Encryption.encoder(password);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
}
