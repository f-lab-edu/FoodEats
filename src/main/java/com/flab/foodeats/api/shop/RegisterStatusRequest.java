package com.flab.foodeats.api.shop;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flab.foodeats.application.shop.RegisterStatusTarget;

public class RegisterStatusRequest {

	@NotNull(message = "Input Your OpenTime")
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime openTime;
	@NotNull(message = "Input Your CloseTime")
	@JsonFormat(pattern = "kk:mm:ss")
	private LocalTime closeTime;
	@NotBlank(message = "Input Your closingDay")
	private String closingDay;

	public RegisterStatusRequest(){

	}

	public RegisterStatusRequest(LocalTime openTime, LocalTime closeTime, String closingDay) {
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.closingDay = closingDay;
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


	public RegisterStatusTarget toParam(String userId) {
		return new RegisterStatusTarget(userId, openTime, closeTime, closingDay);
	}
}
