package com.flab.foodeats.domain.shop;

import java.time.LocalTime;

public class Status {
	private Long shopId;
	private LocalTime openTime;
	private LocalTime closeTime;
	private String closingDay;
	private String shopOpenStatus;

	public Status(Long shopId, LocalTime openTime, LocalTime closeTime, String closingDay, String shopOpenStatus) {
		this.shopId = shopId;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
		this.shopOpenStatus = shopOpenStatus;
	}

	public Status(Long shopId) {
		this.shopId = shopId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public LocalTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}

	public LocalTime getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}

	public String getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}

	public String getShopOpenStatus() {
		return shopOpenStatus;
	}

	public void setShopOpenStatus(String shopOpenStatus) {
		this.shopOpenStatus = shopOpenStatus;
	}
}
