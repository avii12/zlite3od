package Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * class : this class deal with the date and to build the format of the date
 * string
 * 
 * @author Mario, Rohana
 */

public class OrganizeDate {

	public static Date setDateStringFormatFromSQL(String setDate) {

		if (setDate != null) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/YY HH:mm:ss");
			Date date;
			try {
				date = dateFormat.parse(setDate);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public static Date setDateStringFormatFromController(String date, String time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date selectedDate;
		try {
			selectedDate = dateFormat.parse(date + " " + time);
			return selectedDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

}
