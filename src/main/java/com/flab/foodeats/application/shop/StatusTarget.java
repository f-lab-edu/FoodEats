package com.flab.foodeats.application.shop;

import java.time.LocalTime;

import com.flab.foodeats.domain.shop.Status;

public class StatusTarget {

	/**
	 * TODO
	 * 가맹점 오픈 상태
	 * 어노테이션 기반으로 자바에서 디폴트 설정할 수 있는지 확인!
	 */

	private String shopId;
	private LocalTime openTime;
	private LocalTime closeTime;
	private String closingDay;

	public StatusTarget(String shopId, LocalTime openTime, LocalTime closeTime, String closingDay) {
		this.shopId = shopId;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
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

	public Status toEntity(){
		return new Status(shopId,openTime,closeTime,closingDay,"Close");
	}

}
