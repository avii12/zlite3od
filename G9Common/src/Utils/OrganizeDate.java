package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date selectedDate;
		try {
			selectedDate = dateFormat.parse(date + " " + time + ":00");
			return selectedDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
