package com.flab.foodeats.shop.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StatusShopInfo {

	@NotNull(message = "Input Your OpenTime")
	private int openTime;
	@NotNull(message = "Input Your CloseTime")
	private int closeTime;
	@NotBlank(message = "Input Your closingDay")
	private String closingDay;
	private int lastUpdateStatusDay;
	private String shopOpenStatus;

	public StatusShopInfo(int openTime, int closeTime, String closingDay, int lastUpdateStatusDay,
		String shopOpenStatus) {
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
		this.lastUpdateStatusDay = lastUpdateStatusDay;
		this.shopOpenStatus = shopOpenStatus;
	}

	public int getOpenTime() {
		return openTime;
	}

	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	public int getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(int closeTime) {
		this.closeTime = closeTime;
	}

	public String getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}

	public int getLastUpdateStatusDay() {
		return lastUpdateStatusDay;
	}

	public void setLastUpdateStatusDay(int lastUpdateStatusDay) {
		this.lastUpdateStatusDay = lastUpdateStatusDay;
	}

	public String getShopOpenStatus() {
		return shopOpenStatus;
	}

	public void setShopOpenStatus(String shopOpenStatus) {
		this.shopOpenStatus = shopOpenStatus;
	}
}
