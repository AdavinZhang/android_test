package com.example.calendartest;

import java.util.Calendar;

public class CalendarSet {
	Calendar cal;
	int day;
	public CalendarSet(Calendar cal){
		this.cal = cal;
	}
	
	public int getMaxiumDay(){
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public int getMinimumDay(){
		return cal.getActualMinimum(Calendar.DAY_OF_MONTH);
	}
	
	public int getFirstDay(){
		Calendar testCal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_MONTH);
		int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int mod = today%7;
		if(mod<firstDayOfWeek){
			day = firstDayOfWeek - mod;
		}
		else{
			day = firstDayOfWeek+7 - mod;
		}
		return day;
	}

}
