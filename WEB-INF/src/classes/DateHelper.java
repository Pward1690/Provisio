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
	public static String getDate()
		throws ParseException
	{
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YY/mm/dd");
		return formatter.format(date);
	}
}