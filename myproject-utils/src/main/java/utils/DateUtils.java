package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;


/**
 * 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */
public abstract class DateUtils {
	private static final String defaultFormat = "yyyy年MM月dd日 HH:mm:ss";
	private static final String YYYYMM = "yyyyMM";
	private static final String MM = "MM";
	private static final String YYYY = "yyyy";
	private static final String DD = "dd";
	private static final String TIME = "HH:mm:ss";
	private static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
	private static final String YMD = "yyyyMMdd";
	private static final String YMDHMS = "yyyyMMddHHmmss";
	private static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
	private static final String Y_M_D = "yyyy-MM-dd";
	private static final String ymd = "yyyy/MM/dd";
	private static final String ymd_HM = "yyyy/MM/dd HH:mm";
	private static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";
	private static final String china_ymd_HM = "yyyy年MM月dd日 HH点mm分ss秒";

	/**
	 * 得到年龄
	 * @param birthDate
	 * @return
	 */
	public static int getAge(DateTime birthDate) {
		return Years.yearsBetween(birthDate, new DateTime()).getYears();
	}

	/**
	 * 加入现在的时分秒
	 * @param date
	 * @return
	 */
	public static Date addHMSToDate(Date date) {
		if (date == null) {
			return null;
		}
		DateTime dateTime = new DateTime(date);
		Calendar calendar = Calendar.getInstance();
		DateTime now = new DateTime();
		calendar.set(dateTime.getYear(), dateTime.getMonthOfYear() - 1,
		dateTime.getDayOfMonth(), now.getHourOfDay(), now
		.getMinuteOfHour(), now.getSecondOfMinute());
		return new DateTime(calendar).toDate();
	}

	/**
	 * 加入现在的时分秒
	 * @param sdate  yyyy-MM-dd
	 * @return
	 */
	public static Date addHMSToDate(String sdate) {
		if (StringUtils.isEmpty(sdate)) {
			return null;
		}
		String regex = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2]{1,2})-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
		if (!sdate.matches(regex)) {
			return null;
		}
		String[] temp = sdate.split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[2]);
		Date date = new DateTime(year, month, day, 0, 0, 0, 0).toDate();
		return addHMSToDate(date);
	}

	

	/**
	 * 相差时间天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {
		if ((fDate == null) || (oDate == null)) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / 86400000L);
	}

	/**
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(oDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;
	}

	/**
	 * 
	 * @return
	 */
	public static Calendar getCalender() {
		DateTime dt = new DateTime();
		return dt.toCalendar(Locale.CHINA);
	}

	/**
	 * 
	 * @return
	 */
	public static Date getDate() {
		DateTime dt = new DateTime();
		return dt.toDate();
	}

	/**
	 * 返回dd
	 * @return
	 */
	public static String getDays() {
		DateTime dt = new DateTime();
		return dt.toString("dd");
	}

	/**
	 * 格式化时间
	 * @param date 时间
	 * @param pattern 格式  默认格式为：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		DateTime dt = new DateTime(date);
		if (pattern == null) {
			pattern = Y_M_D_HMS;
		}
		return dt.toString(pattern);
	}

	/**
	 * 格式化当前时间  
	 * @param pattern 格式 默认yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDate(String pattern) {
		DateTime dt = new DateTime();
		if (pattern == null) {
			pattern = Y_M_D_HMS;
		}
		return dt.toString(pattern);
	}

	/**
	 * 格式时间  yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		DateTime dt = new DateTime(date);
		return dt.toString("yyyy-MM-dd HH:mm");
	}

	/**
	 * 得到明天的日期
	 * @return
	 */
	public static Date getNextDay() {
		DateTime dt = new DateTime();
		return dt.plusDays(1).toDate();
	}

    /**
     * 得到当前的年分  yyyy
     * @return
     */
	public static String getYDate() {
		DateTime dt = new DateTime();
		return dt.toString("yyyy");
	}

	/**
	 * 得到当前的时间   HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime() {
		DateTime dt = new DateTime();
		return dt.toString("HH:mm:ss");
	}

	/**
	 * 得到当前的时间字符串  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDate() {
		DateTime dt = new DateTime();
		return dt.toString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间的字符串    默认yyyy-MM-dd HH:mm:ss
	 * @param pattern 格式
	 * @return
	 */
	public static String nowDate(String pattern) {
		if (pattern == null) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		DateTime dt = new DateTime();
		return dt.toString(pattern);
	}

	/**
	 * 得到当前时间  yyyyMMddHHmmss
	 * @return
	 */
	public static String getYMDHMSDay() {
		DateTime dt = new DateTime();
		return dt.plusDays(1).toString("yyyyMMddHHmmss");
	}

	/**
	 * 转换中国年日期
	 * 
	 * @param date
	 * @return
	 */
	public static String chinaSmartFormat(Date date) {
		String dateStr = null;
		if (date == null) {
			dateStr = "";
		} else {
			try {
				dateStr = formatDate(date, china_ymd_HM);
				// 时分
				if (dateStr.endsWith("00点00分00秒")) {
					dateStr = dateStr.substring(0, dateStr.indexOf(" "));
				}// 分
				else if (dateStr.endsWith("00分00秒")) {
					dateStr = dateStr.substring(0, dateStr.indexOf("点") + 1);
				}
				// 秒
				else if (dateStr.endsWith("00秒")) {
					dateStr = dateStr.substring(0, dateStr.indexOf("分") + 1);
				}
			} catch (Exception ex) {
				throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
			}
		}
		return dateStr;
	}
	

	/**
	 * 把字符串格式化成日期
	 * 
	 * @param argDateStr
	 * @param argFormat
	 * @return
	 */
	public static Date formatStringToDate(String argDateStr, String argFormat) throws Exception {
		if (argDateStr == null || argDateStr.trim().length() < 1) {
			throw new Exception("参数[日期]不能为空!");
		}
		String strFormat = argFormat;
		if (StringUtils.isEmpty(strFormat)) {
			strFormat = Y_M_D;
			if (argDateStr.length() > 16) {
				strFormat = Y_M_D_HMS;
			} else if (argDateStr.length() > 10) {
				strFormat = Y_M_D_HM;
			}
		}
		SimpleDateFormat sdfFormat = new SimpleDateFormat(strFormat);
		// 严格模式
		sdfFormat.setLenient(false);
		try {
			return sdfFormat.parse(argDateStr);
		} catch (ParseException e) {
			throw new Exception(e);
		}
	}

}

