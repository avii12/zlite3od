package Orders;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConvert {
	public static Date buildMySqlDateTimeFormatFromTextFields(String date, String time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d;
		try {
			d = dateFormat.parse(date + " " + time + ":00");
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Date buildMySqlDateTimeFormatFromDateTimeString(String datetime) {
		if(datetime != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d;
			try {
				d = dateFormat.parse(datetime);
				return d;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public static String convertMySqlDateTimeFormatToString(Timestamp date) {
		if(date != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.format(date);
		}
		return null;
	}

	public static String getTimeNowInMySqlDateTimeFormat_String() {
		return convertMySqlDateTimeFormatToString(getTimeNowInMySqlDateTimeFormat_Date());
	}


	public static Timestamp getTimeNowInMySqlDateTimeFormat_Date() {
		return  new Timestamp(null, null);
	}


}
