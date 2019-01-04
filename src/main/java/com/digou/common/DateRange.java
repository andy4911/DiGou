package com.digou.common;

import java.util.Calendar;
import java.util.Date;

public class DateRange {
	public enum RangeType {
		YEAR, MONTH, WEEK, DAY;
	}
	public enum BeginOrEnd {
		BEGIN, END;
	}
	
	public static long getTimeStamp(RangeType type, BeginOrEnd origin, Date date) {
		Calendar c = Calendar.getInstance();  
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);

		if (origin == BeginOrEnd.END) {
			switch (type) {
			case YEAR:
				c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
				break;
			case MONTH:
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			case WEEK:
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				break;
			default:
				break;
			}
			c.set(Calendar.HOUR_OF_DAY, 23);  
			c.set(Calendar.MINUTE, 59);  
			c.set(Calendar.SECOND,59);  
			c.set(Calendar.MILLISECOND, 999);  
			return c.getTimeInMillis();
		} else {
			switch (type) {
			case YEAR:
				c.set(Calendar.DAY_OF_YEAR, 1);
				break;
			case MONTH:
				c.set(Calendar.DAY_OF_MONTH, 1);
				break;
			case WEEK:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				break;
			default:
				break;
			}
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);  
			c.set(Calendar.SECOND,0);  
			c.set(Calendar.MILLISECOND, 0);  
			return c.getTimeInMillis();
		}
	}

	
}
