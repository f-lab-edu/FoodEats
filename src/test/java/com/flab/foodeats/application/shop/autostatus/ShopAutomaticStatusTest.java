package com.flab.foodeats.application.shop.autostatus;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.flab.foodeats.application.shop.StatusTarget;
import com.flab.foodeats.domain.shop.Status;

class ShopAutomaticStatusTest {

	@Test
	@DisplayName("OpenTime < CloseTime - OPEN")
	void doNotChangeDayOpenTest() {
		int openTime = 6;
		int closeTime = 13;
		int now = 9;

		boolean result = new ShopAutomaticStatus().doNotChangeDayInOpenStatus(openTime,closeTime,now);
		assertThat(result, is(true));
	}

	@Test
	@DisplayName("OpenTime < CloseTime - CLOSE")
	void doNotChangeDayCloseTest() {
		int openTime = 6;
		int closeTime = 13;
		int now = 19;

		boolean result = new ShopAutomaticStatus().doNotChangeDayInOpenStatus(openTime,closeTime,now);
		assertThat(result, is(false));
	}

	@Test
	@DisplayName("OpenTime > CloseTime - OPEN")
	void ChangeDayOpenTest() {
		int openTime = 17;
		int closeTime = 3;
		int now = 22;

		boolean result = new ShopAutomaticStatus().changeDayInOpenStatus(openTime,closeTime,now);
		assertThat(result, is(true));
	}

	@Test
	@DisplayName("OpenTime > CloseTime - CLOSE")
	void ChangeDayCloseTest() {
		int openTime = 17;
		int closeTime = 3;
		int now = 4;

		boolean result = new ShopAutomaticStatus().changeDayInOpenStatus(openTime,closeTime,now);
		assertThat(result, is(false));
	}

	/**
	 * changeShopStatusAuto는 now()가 사용되기 때문에
	 * changeShopStatusAuto메서드의 테스트 코드 작성은 결과가 항상 동일하지 않습니다.
	 */
	@Test
	@DisplayName("현재 상태 ")
	void changeShopStatusAuto(){
		LocalTime openTime = LocalTime.parse("12:03:02",  DateTimeFormatter.ofPattern("HH:mm:ss"));
		LocalTime closeTime = LocalTime.parse("16:03:02",  DateTimeFormatter.ofPattern("HH:mm:ss"));
		Status status = new Status(1L,openTime,closeTime,"NO","CLOSE");

		boolean result = new ShopAutomaticStatus().changeShopStatusAuto(status);
		assertThat(result, is(false));
	}
}