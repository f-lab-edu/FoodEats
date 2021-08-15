package com.flab.foodeats.shop.holiday;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.ibm.icu.util.ChineseCalendar;

public class HolidayCheck {

	static Set<String> holidaysSet = new HashSet<>();
	public static final int LD_SUNDAY = 7;
	public static final int LD_SATURDAY = 6;

	public HolidayCheck() {
	}

	// 공휴일 체크
	public boolean holidayCheck() {
		// 현재 시간 문자열로 포맷
		LocalDateTime now = LocalDateTime.now();
		String formatDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// 올해 공휴일 HashSet에 설정
		int currentYear = now.getYear();
		addHolidayAndReplacedholiday(Integer.toString(currentYear));

		// 오늘 공휴일인지 체크 -> 결과 반환
		if (holidaysSet.contains(formatDate)) {
			return true; //휴일
		}
		return false; //휴일x
	}

	// 음력 > 양력
	public String changeLunarToSolar(String yyyymmdd) {
		ChineseCalendar chineseCalendar = new ChineseCalendar();

		if (yyyymmdd == null)
			return null;

		String date = yyyymmdd.trim();
		if (date.length() != 8) {
			if (date.length() == 4)
				date = date + "0101";
			else if (date.length() == 6)
				date = date + "01";
			else if (date.length() > 8)
				date = date.substring(0, 8);
			else
				return null;
		}

		chineseCalendar.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(0, 4)) + 2637);   // 년, year + 2637
		chineseCalendar.set(ChineseCalendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);              // 월, month -1
		chineseCalendar.set(ChineseCalendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));              // 일

		LocalDate solar = Instant.ofEpochMilli(chineseCalendar.getTimeInMillis())
			.atZone(ZoneId.of("UTC"))
			.toLocalDate();

		int solarYear = solar.getYear();
		int solarMonth = solar.getMonth().getValue();
		int solarDay = solar.getDayOfMonth();

		StringBuilder ret = new StringBuilder();
		ret.append(String.format("%04d", solarYear));
		ret.append(String.format("%02d", solarMonth));
		ret.append(String.format("%02d", solarDay));

		return ret.toString();
	}

	private String seperateLunarYearAndDay(String yyyy, String date) {
		return changeLunarToSolar(yyyy + date).substring(4);
	}

	public Set<String> addHolidayAndReplacedholiday(String yyyy) {
		holidaysSet.clear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		// 양력 휴일
		holidaysSet.add(yyyy + "0101");   // 신정
		holidaysSet.add(yyyy + "0301");   // 삼일절
		holidaysSet.add(yyyy + "0505");   // 어린이날
		holidaysSet.add(yyyy + "0606");   // 현충일
		holidaysSet.add(yyyy + "0815");   // 광복절
		holidaysSet.add(yyyy + "1003");   // 개천절
		holidaysSet.add(yyyy + "1009");   // 한글날
		holidaysSet.add(yyyy + "1225");   // 성탄절

		// 음력 휴일
		String prev_seol = LocalDate.parse(changeLunarToSolar(yyyy + "0101"), formatter)
			.minusDays(1)
			.toString()
			.replace("-", "");
		holidaysSet.add(yyyy + prev_seol.substring(4));        // ""
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0101"));  // 설날
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0102"));  // 설날
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0103"));  // 설날
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0408"));  // 석탄일
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0814"));  // 추석
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0815"));  // 추석
		holidaysSet.add(yyyy + seperateLunarYearAndDay(yyyy, "0816"));  // 추석

		// 대체공휴일 지정
		try {
			// 어린이날
			int childDayChk = LocalDate.parse(yyyy + "0505", formatter).getDayOfWeek().getValue();
			if (childDayChk == LD_SUNDAY) {      // 일요일
				holidaysSet.add(yyyy + "0506");
			}
			if (childDayChk == LD_SATURDAY) {  // 토요일
				holidaysSet.add(yyyy + "0507");
			}

			// 신정
			int newYearDay = LocalDate.parse(yyyy + "0101", formatter).getDayOfWeek().getValue();
			if (newYearDay == LD_SUNDAY) {      // 일요일
				holidaysSet.add(yyyy + "0102");
			}
			if (newYearDay == LD_SATURDAY) {  // 토요일
				holidaysSet.add(yyyy + "0103");
			}

			// 삼일절
			int independenceMovementDay = LocalDate.parse(yyyy + "0301", formatter).getDayOfWeek().getValue();
			if (independenceMovementDay == LD_SUNDAY) {      // 일요일
				holidaysSet.add(yyyy + "0302");
			}
			if (independenceMovementDay == LD_SATURDAY) {  // 토요일
				holidaysSet.add(yyyy + "0303");
			}

			/**
			 * Todo
			 * 양력 휴일 나머지 구현
			 */

			// 석탄일
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0408"), formatter).getDayOfWeek().getValue()
				== LD_SATURDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0410"));
			}
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0408"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0409"));
			}

			// 설날 대체공휴일 검사 , 대체공휴일은 단 하루
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0101"), formatter).getDayOfWeek().getValue() == LD_SATURDAY
				||
				LocalDate.parse(changeLunarToSolar(yyyy + "0101"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0104"));
			}
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0102"), formatter).getDayOfWeek().getValue() == LD_SATURDAY
				||
				LocalDate.parse(changeLunarToSolar(yyyy + "0102"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0104"));
			}
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0103"), formatter).getDayOfWeek().getValue()
				== LD_SATURDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0105"));
			}
			if (
				LocalDate.parse(changeLunarToSolar(yyyy + "0103"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0104"));
			}

			// 추석 대체공휴일 검사 , 대체공휴일은 단 하루
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0814"), formatter).getDayOfWeek().getValue() == LD_SATURDAY
				||
				LocalDate.parse(changeLunarToSolar(yyyy + "0814"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0817"));
			}
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0815"), formatter).getDayOfWeek().getValue() == LD_SATURDAY
				||
				LocalDate.parse(changeLunarToSolar(yyyy + "0815"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0817"));
			}
			if (LocalDate.parse(changeLunarToSolar(yyyy + "0816"), formatter).getDayOfWeek().getValue()
				== LD_SATURDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0818"));
			}
			if (
				LocalDate.parse(changeLunarToSolar(yyyy + "0816"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
				holidaysSet.add(changeLunarToSolar(yyyy + "0817"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return holidaysSet;
	}

}
