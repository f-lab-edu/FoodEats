package com.flab.foodeats.domain.shop;

import java.time.LocalTime;

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
@Table(name = "shop_business")
@Entity
@ToString
public class BusinessHour {

	@Id
	@Column(name = "shop_business_no")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;

	@Column(name = "business_open_time", nullable = false)
	private LocalTime openTime;

	@Column(name = "business_close_time", nullable = false)
	private LocalTime closeTime;

	@Column(name = "business_closing_day", nullable = false)
	private String closingDay;

	@Column(name = "business_open_status", nullable = false)
	private String shopOpenStatus;

	// @OneToOne(mappedBy = "businessHour")
	// private Shop shop;

	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "shop")
	// private Shop shop;

	public BusinessHour() {

	}

	@Builder
	public BusinessHour(Long shopId, LocalTime openTime, LocalTime closeTime, String closingDay,
		String shopOpenStatus) {
		this.shopId = shopId;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
		this.shopOpenStatus = shopOpenStatus;

	}

	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}

	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}

	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}

	public void setShopOpenStatus(String shopOpenStatus) {
		this.shopOpenStatus = shopOpenStatus;
	}

}
