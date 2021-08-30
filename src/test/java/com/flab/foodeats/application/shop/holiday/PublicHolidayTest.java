package com.flab.foodeats.application.shop.holiday;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PublicHolidayTest {

	static Set<String> holidaysSet = new HashSet<>();
	static int currentYear;
	static String currentDay;


	@BeforeEach
	void setUp() {
		LocalDateTime now = LocalDateTime.now();
		currentYear = now.getYear();
		currentDay = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		holidaysSet = new PublicHoliday().setPublicHoliday(Integer.toString(currentYear))
			.stream()
			.collect(Collectors.toSet());
	}

	@Test
	@DisplayName("오늘 확인 / calculator 메소드 자체 확인")
	void calculator() {

		boolean validCalculatorMethod = new PublicHoliday().calculator();
		boolean validListData = holidaysSet.contains(currentDay) ? true : false;

		assertThat(validCalculatorMethod, is(validListData));
	}

	@Test
	@DisplayName("올해 어린이날")
	void childDay() {
		assertThat(holidaysSet.contains("20210505"), is(true)); //수
	}

	@Test
	@DisplayName("올해 어린이날 / 대체공휴일 X")
	void validReplacedChildDay() {
		assertThat(holidaysSet.contains("20210506"), is(false)); //목
	}

	@Test
	@DisplayName("올해 추석")
	void thanksGivingDay() {
		assertThat(holidaysSet.contains("20210920"), is(true)); //월
		assertThat(holidaysSet.contains("20210921"), is(true)); //화
		assertThat(holidaysSet.contains("20210922"), is(true)); //수
	}

	@Test
	@DisplayName("올해 추석 다음날 / 대체공휴일 x")
	void validReplacedThanksGivingDay() {
		assertThat(holidaysSet.contains("20210923"), is(false)); //목
	}

	@Test
	@DisplayName("올해 설날")
	void LunarNewYear() {
		assertThat(holidaysSet.contains("20210211"), is(true)); //목
		assertThat(holidaysSet.contains("20210212"), is(true)); //금
		assertThat(holidaysSet.contains("20210213"), is(true)); //토
	}

	@Test
	@DisplayName("올해 설날 다음날 / 대체공휴일 o")
	void validReplacedLunarNewYear() {
		assertThat(holidaysSet.contains("20210214"), is(false)); // 일, 그냥 주말, 공휴일x, 대체공휴일x
		assertThat(holidaysSet.contains("20210215"), is(true));  // 월, 대체공휴일
	}

	@Test
	@DisplayName(" 2020년 삼일절 일요일")
	void independenceMovementDay() {
		assertThat(new PublicHoliday().setPublicHoliday("2020").contains("20200301"), is(true)); //일
	}

	@Test
	@DisplayName(" 2020년 삼일절 대체공휴일 월요일")
	void validReplacedIndependenceMovementDay() {
		assertThat(new PublicHoliday().setPublicHoliday("2020").contains("20200302"), is(true)); //월
	}

}

