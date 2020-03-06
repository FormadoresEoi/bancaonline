package es.eoi.mundobancario.util;

import java.util.Calendar;
import java.util.Date;

public class Util_dates {

	 public static Date getDateWithoutTime() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	 
	    return calendar.getTime();
	}
	 
	 public static Calendar convertDateWithoutTime(Calendar calendar) {
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		 
		    return calendar;
		}
}
