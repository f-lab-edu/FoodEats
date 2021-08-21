package com.flab.foodeats.shop.autostatus;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.flab.foodeats.shop.model.StatusShopInfo;

@Service
public class ShopAutomaticStatus {

	public boolean changeShopStatusAuto(StatusShopInfo statusShopInfo) {

		LocalDateTime now = LocalDateTime.now();
		int currentHour = now.getHour();
		int shopOpenTime = statusShopInfo.getOpenTime().getHour();
		int shopCloseTime = statusShopInfo.getCloseTime().getHour();

		// ex) 13시 ~ 새벽 3시
		if (shopOpenTime > shopCloseTime) {
			// 종료를 상태 검증
			if (!changeDayInOpenStatus(shopOpenTime, shopCloseTime, currentHour)) {
				return false; // false = Close
			} else {
				return true; // true = Open
			}

		}
		// ex) 13시 ~ 18시
		else {
			// 시작 상태를 검증
			if (doNotChangeDayInOpenStatus(shopOpenTime, shopCloseTime, currentHour)) {
				return true;
			} else {
				return false;
			}
		}
	}

	// 가게 운영시간에 날이 바뀜
	public boolean changeDayInOpenStatus(int shopOpenTime, int shopCloseTime, int currentHour) {
		if (shopCloseTime <= currentHour && shopOpenTime - 1 >= currentHour) {
			return false;
		} else {
			return true;
		}
	}

	// 가게 운영시간에 날이 바뀌지 않음
	public boolean doNotChangeDayInOpenStatus(int shopOpenTime, int shopCloseTime, int currentHour) {
		if (shopOpenTime <= currentHour && shopCloseTime - 1 >= currentHour) {
			return true;
		} else {
			return false;
		}
	}

}

