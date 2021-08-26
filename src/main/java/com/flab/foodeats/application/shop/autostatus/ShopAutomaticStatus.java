package com.flab.foodeats.application.shop.autostatus;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.flab.foodeats.domain.shop.Status;

@Service
public class ShopAutomaticStatus {

	public boolean changeShopStatusAuto(Status statusInfo) {

		LocalDateTime now = LocalDateTime.now();
		int currentHour = now.getHour();
		int shopOpenTime = statusInfo.getOpenTime().getHour();
		int shopCloseTime = statusInfo.getCloseTime().getHour();

		// ex) 13시 ~ 새벽 3시
		if (shopOpenTime > shopCloseTime) {
			// 종료를 상태 검증
			return changeDayInOpenStatus(shopOpenTime, shopCloseTime, currentHour);
		}

		// ex) 13시 ~ 18시
		if (shopOpenTime < shopCloseTime) {
			// 시작 상태를 검증
			return doNotChangeDayInOpenStatus(shopOpenTime, shopCloseTime, currentHour);
		}

		return false;
	}

	// 가게 운영시간에 날이 바뀜
	public boolean changeDayInOpenStatus(int shopOpenTime, int shopCloseTime, int currentHour) {
		if (shopCloseTime <= currentHour && shopOpenTime - 1 >= currentHour) {
			return false;
		}
		return true;
	}

	// 가게 운영시간에 날이 바뀌지 않음
	public boolean doNotChangeDayInOpenStatus(int shopOpenTime, int shopCloseTime, int currentHour) {
		if (shopOpenTime <= currentHour && shopCloseTime - 1 >= currentHour) {
			return true;
		}
		return false;
	}

}

