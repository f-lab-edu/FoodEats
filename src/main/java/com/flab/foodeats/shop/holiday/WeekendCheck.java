package com.flab.foodeats.shop.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendCheck {

	public WeekendCheck() {
	}

	public boolean weekendCheck(){
		DayOfWeek week = LocalDate.now().getDayOfWeek();

		if(week.getValue() == 6 || week.getValue() == 7){
			return true;
		}
		return false;
	}
}
