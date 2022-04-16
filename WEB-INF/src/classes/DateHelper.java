/**
 * Capstone Provisio Project
 * Green Team
 * 04/14/2022
 */

package Provisio;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Date helper class
 */
class DateHelper {
	/**
	 * Get current date in MySQL format
	 * @return String
	 */
	public static String getDate()
		throws ParseException
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YY/mm/dd");
		return formatter.format(date);
	}

	/**
	 * Validate date (generically)
	 * @return Boolean
	 */
	public static Boolean isValidDate(
		String date
	){
		return date.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}+$");
	}
}