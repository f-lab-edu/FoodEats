package com.flab.foodeats.application.shop.holiday;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import com.ibm.icu.util.ChineseCalendar;

public class PublicHoliday {

	static Set<String> holidaysSet = new HashSet<>();
	public static final int LD_SUNDAY = 7;
	public static final int LD_SATURDAY = 6;

	public boolean calculator() {
		LocalDateTime now = LocalDateTime.now();
		String formatDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		int currentYear = now.getYear();
		setPublicHoliday(Integer.toString(currentYear));

		if (holidaysSet.contains(formatDate)) {
			return true; //휴일
		}
		return false; //휴일x
	}


	public Set<String> setPublicHoliday(String yyyy) {
		holidaysSet.clear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		// 양력 공휴일
		addSolarHoliday(yyyy);
		// 음력 공휴일
		addLunarHoliday(yyyy, formatter);
		// 대체 공휴일
		addReplacedHoliday(yyyy, formatter);

		return holidaysSet;
	}


	private void addReplacedHoliday(String yyyy, DateTimeFormatter formatter){
		// 양력 대체 공휴일
		addReplacedSolarHoliday(yyyy, formatter);
		// 음력 대체 공휴일
		addReplacedLunarHoliday(yyyy, formatter);
	}


	private void addReplacedSolarHoliday(String yyyy, DateTimeFormatter formatter){
		// 어린이날
		addReplacedChildDay(yyyy, formatter);
		// 신정
		addReplacedNewYearDay(yyyy, formatter);
		// 삼일절
		addReplacedIndependenceMovementDay(yyyy, formatter);

		/**
		 * Todo
		 * 대체 공휴일 나머지 구현
		 */
	}

	private void addReplacedLunarHoliday(String yyyy, DateTimeFormatter formatter){
		// 석탄일
		addReplacedBuddhaBirthday(yyyy, formatter);
		// 설날 대체공휴일 검사 , 대체공휴일은 단 하루
		addReplacedLunarNewYear(yyyy, formatter);
		// 추석 대체공휴일 검사 , 대체공휴일은 단 하루
		addReplacedThanksgivingDay(yyyy, formatter);
	}

	private void addSolarHoliday(String yyyy){
		holidaysSet.add(yyyy + "0101");   // 신정
		holidaysSet.add(yyyy + "0301");   // 삼일절
		holidaysSet.add(yyyy + "0505");   // 어린이날
		holidaysSet.add(yyyy + "0606");   // 현충일
		holidaysSet.add(yyyy + "0815");   // 광복절
		holidaysSet.add(yyyy + "1003");   // 개천절
		holidaysSet.add(yyyy + "1009");   // 한글날
		holidaysSet.add(yyyy + "1225");   // 성탄절
	}

	private void addLunarHoliday(String yyyy, DateTimeFormatter formatter){
		String prev_seol = LocalDate.parse(changeLunarToSolar(yyyy + "0101"), formatter)
			.minusDays(1)
			.toString()
			.replace("-", "");
		holidaysSet.add(yyyy + prev_seol.substring(4));        // ""
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0101"));  // 설날
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0102"));  // 설날
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0103"));  // 설날
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0408"));  // 석탄일
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0814"));  // 추석
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0815"));  // 추석
		holidaysSet.add(yyyy + separateLunarYearAndDay(yyyy, "0816"));  // 추석
	}

	private void addReplacedChildDay(String yyyy, DateTimeFormatter formatter) {
		int childDayChk = LocalDate.parse(yyyy + "0505", formatter).getDayOfWeek().getValue();
		if (childDayChk == LD_SUNDAY) {      // 일요일
			holidaysSet.add(yyyy + "0506");
		}
		if (childDayChk == LD_SATURDAY) {  // 토요일
			holidaysSet.add(yyyy + "0507");
		}
	}
	private void addReplacedNewYearDay(String yyyy, DateTimeFormatter formatter) {
		int newYearDay = LocalDate.parse(yyyy + "0101", formatter).getDayOfWeek().getValue();
		if (newYearDay == LD_SUNDAY) {      // 일요일
			holidaysSet.add(yyyy + "0102");
		}
		if (newYearDay == LD_SATURDAY) {  // 토요일
			holidaysSet.add(yyyy + "0103");
		}
	}
	private void addReplacedIndependenceMovementDay(String yyyy, DateTimeFormatter formatter) {
		int independenceMovementDay = LocalDate.parse(yyyy + "0301", formatter).getDayOfWeek().getValue();
		if (independenceMovementDay == LD_SUNDAY) {      // 일요일
			holidaysSet.add(yyyy + "0302");
		}
		if (independenceMovementDay == LD_SATURDAY) {  // 토요일
			holidaysSet.add(yyyy + "0303");
		}
	}

	private void addReplacedBuddhaBirthday(String yyyy, DateTimeFormatter formatter) {
		if (LocalDate.parse(changeLunarToSolar(yyyy + "0408"), formatter).getDayOfWeek().getValue()
			== LD_SATURDAY) {
			holidaysSet.add(changeLunarToSolar(yyyy + "0410"));
		}
		if (LocalDate.parse(changeLunarToSolar(yyyy + "0408"), formatter).getDayOfWeek().getValue() == LD_SUNDAY) {
			holidaysSet.add(changeLunarToSolar(yyyy + "0409"));
		}
	}

	private void addReplacedLunarNewYear(String yyyy, DateTimeFormatter formatter) {
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
	}

	private void addReplacedThanksgivingDay(String yyyy, DateTimeFormatter formatter) {
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
	}


	// 음력 > 양력
	private String changeLunarToSolar(String yyyymmdd) {
		ChineseCalendar chineseCalendar = new ChineseCalendar();

		String validedDate = validStringLength(yyyymmdd);
		if(validedDate == null){
			throw new NullPointerException("Holiday Input Null");
		}

		String ChangedSolarDate = applySolar(chineseCalendar, validedDate);

		return ChangedSolarDate;
	}

	private String validStringLength(String yyyymmdd){
		/*
		* 오류가 날 수 없는 구조
		* 입력을 LocalDateTime으로 오늘 날짜를 입력
		*/

		if (yyyymmdd == null)
			return null;

		String date = yyyymmdd.trim();
		if (date.length() != 8)
			return null;

		return date;
	}

	private String applySolar(ChineseCalendar chineseCalendar, String date){
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

	private String separateLunarYearAndDay(String yyyy, String date) {
		return changeLunarToSolar(yyyy + date).substring(4);
	}

}