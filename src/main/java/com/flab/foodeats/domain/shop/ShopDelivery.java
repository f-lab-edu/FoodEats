package com.flab.foodeats.domain.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Table(name = "shop_delivery")
@Entity
@ToString
public class ShopDelivery {

	@Id
	@Column(name = "shop_delivery_no")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;

	@Column(name = "shop_grade", nullable = false)
	private String grade;

	@Column(name = "delivery_method", nullable = false)
	private String deliveryMethod;

	@Column(name = "payment_method", nullable = false)
	private String shopPaymentMethod;

	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "shop")
	// private Shop shop;

	// @OneToOne(mappedBy = "shopDelivery")
	// private Shop shop;


	public ShopDelivery() {

	}

	@Builder
	public ShopDelivery(Long shopId, String grade, String deliveryMethod, String shopPaymentMethod) {
		this.shopId = shopId;
		this.grade = grade;
		this.deliveryMethod = deliveryMethod;
		this.shopPaymentMethod = shopPaymentMethod;

	}


}
