package com.flab.foodeats.application.shop;

import java.time.LocalTime;

import com.flab.foodeats.domain.shop.BusinessHour;

public class BusinessHourTarget {

	private Long shopId;
	private LocalTime openTime;
	private LocalTime closeTime;
	private String closingDay;

	public BusinessHourTarget(Long shopId, LocalTime openTime, LocalTime closeTime, String closingDay) {
		this.shopId = shopId;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
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

	public BusinessHour toEntity(){
		return new BusinessHour(shopId,openTime,closeTime,closingDay,"Close");
	}

}
