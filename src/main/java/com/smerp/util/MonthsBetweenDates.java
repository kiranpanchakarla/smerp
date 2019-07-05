package com.smerp.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
@Component
public class MonthsBetweenDates {

	
	public List<String> getMonthsBetweenDates(Date d1, Date d2) {

		List<String> monthsList = new ArrayList<>();

		Calendar beginCalendar = Calendar.getInstance();
		Calendar finishCalendar = Calendar.getInstance();

		beginCalendar.setTime(d1);
		finishCalendar.setTime(d2);

		SimpleDateFormat formaterYd = new SimpleDateFormat("MMM");

		while (beginCalendar.before(finishCalendar)) {

			String month = formaterYd.format(beginCalendar.getTime()).toUpperCase();
			monthsList.add(month);
			// Add One Month to get next Month
			beginCalendar.add(Calendar.MONTH, 1);
		}

		String month = formaterYd.format(finishCalendar.getTime()).toUpperCase(); // add last month
		monthsList.add(month);

		List<String> uniqueMonthList = monthsList.stream().distinct().collect(Collectors.toList()); // No duplicates

		return uniqueMonthList;
	}
	
}
