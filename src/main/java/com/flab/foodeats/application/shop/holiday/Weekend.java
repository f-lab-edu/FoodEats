package com.flab.foodeats.application.shop.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Weekend {

	public boolean weekendCheck(){
		DayOfWeek week = LocalDate.now().getDayOfWeek();

		if(week.getValue() == 6 || week.getValue() == 7){
			return true;
		}
		return false;
	}
}
