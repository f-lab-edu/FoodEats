package com.flab.foodeats.shop.model;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatusShopInfo {

	private int shopId;
	@NotNull(message = "Input Your OpenTime")
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime openTime;
	@NotNull(message = "Input Your CloseTime")
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime closeTime;
	@NotBlank(message = "Input Your closingDay")
	private String closingDay;
	private String shopOpenStatus;

	public StatusShopInfo(){

	}

	public StatusShopInfo(int shopId, LocalTime openTime, LocalTime closeTime, String closingDay,
		String shopOpenStatus) {
		this.shopId = shopId;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
		this.shopOpenStatus = shopOpenStatus;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
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

	@Override
	public String toString() {
		return "StatusShopInfo{" +
			"shopId=" + shopId +
			", openTime=" + openTime +
			", closeTime=" + closeTime +
			", closingDay='" + closingDay + '\'' +
			", shopOpenStatus='" + shopOpenStatus + '\'' +
			'}';
	}
}
