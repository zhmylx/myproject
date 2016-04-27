package com.cpersicum.modules.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;

public abstract class DateUtils {
	private static final String defaultFormat = "yyyy年MM月dd日 EE HH:mm:ss";
	private static final String YYYYMM = "yyyyMM";
	private static final String MM = "MM";
	private static final String YYYY = "yyyy";
	private static final String DD = "dd";
	private static final String TIME = "HH:mm:ss";
	private static final String YYYYMMddHHMM = "yyyy-MM-dd HH:mm";
	private static final String YYYYMMdd = "yyyyMMdd";
	private static final String YYYYMMddHHMMSS = "yyyyMMddHHmmss";

	public static int getAge(DateTime birthDate) {
		return Years.yearsBetween(birthDate, new DateTime()).getYears();
	}

	public static Date addHMSToDate(Date date) {
		if (date == null) {
			return null;
		}
		DateTime dateTime = new DateTime(date);
		Calendar calendar = Calendar.getInstance();
		DateTime now = new DateTime();
		calendar.set(dateTime.getYear(), dateTime.getMonthOfYear() - 1,
				dateTime.getDayOfMonth(), now.getHourOfDay(),
				now.getMinuteOfHour(), now.getSecondOfMinute());
		return new DateTime(calendar).toDate();
	}

	public static Date addHMSToDate(String sdate) {
		if (StringUtils.isEmpty(sdate)) {
			return null;
		}
		String regex = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2]{1,2})-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
		if (!(sdate.matches(regex))) {
			return null;
		}
		String[] temp = sdate.split("-");
		int year = Integer.parseInt(temp[0]);
		int month = Integer.parseInt(temp[1]);
		int day = Integer.parseInt(temp[2]);
		Date date = new DateTime(year, month, day, 0, 0, 0, 0).toDate();
		return addHMSToDate(date);
	}

	public boolean matchYMDSMS() {
		DateTime dt = new DateTime();
		dt.getHourOfDay();
		return false;
	}

	public static int getIntervalDays(Date fDate, Date oDate) {
		if ((fDate == null) || (oDate == null)) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / 86400000L);
	}

	public static int daysOfTwo(Date fDate, Date oDate) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(fDate);
		int day1 = aCalendar.get(6);
		aCalendar.setTime(oDate);
		int day2 = aCalendar.get(6);
		return (day2 - day1);
	}

	public static String formatPlusDate(Date date, int plus) {
		if (date == null) {
			return new DateTime().plus(plus).toString("yyyy-MM-dd HH:mm");
		}
		DateTime dt = new DateTime(date);
		return dt.plusYears(plus).toString("yyyy-MM-dd HH:mm");
	}

	public static Date plusDate(Date date, int plus) {
		if (date == null) {
			return new DateTime().plus(plus).toDate();
		}
		DateTime dt = new DateTime(date);
		return dt.plusYears(plus).toDate();
	}

	public static Date plusDayDate(Date date, int plus) {
		if (date == null) {
			return new DateTime().plusDays(plus).toDate();
		}
		DateTime dt = new DateTime(date);
		return dt.plusDays(plus).toDate();
	}

	public static Date plusYearDate(Date date, int plus) {
		if (date == null) {
			return new DateTime().plusYears(plus).toDate();
		}
		DateTime dt = new DateTime(date);
		return dt.plusYears(plus).toDate();
	}

	public static Date plusMonthsDate(Date date, int plus) {
		if (date == null) {
			return new DateTime().plusMonths(plus).toDate();
		}
		DateTime dt = new DateTime(date);
		return dt.plusMonths(plus).toDate();
	}

	public static String getNextMonth() {
		DateTime dt = new DateTime();
		return dt.plusMonths(1).toString("MM");
	}

	public static Date getPlusDay(int plus) {
		DateTime dt = new DateTime();
		return dt.plusDays(plus).toDate();
	}

	public static Calendar getCalender() {
		DateTime dt = new DateTime();
		return dt.toCalendar(Locale.CHINA);
	}

	public static Date getDate() {
		DateTime dt = new DateTime();
		return dt.toDate();
	}

	public static String getDays() {
		DateTime dt = new DateTime();
		return dt.toString("dd");
	}

	public static String formatDate(Date date, String pattern) {
		DateTime dt = new DateTime(date);
		if (pattern == null) {
			pattern = "yyyy年MM月dd日 EE HH:mm:ss";
		}
		return dt.toString(pattern);
	}

	public static String formatDate(String pattern) {
		DateTime dt = new DateTime();
		if (pattern == null) {
			pattern = "yyyy年MM月dd日 EE HH:mm:ss";
		}
		return dt.toString(pattern);
	}

	public static String formatDate(Date date) {
		DateTime dt = new DateTime(date);
		return dt.toString("yyyy-MM-dd HH:mm");
	}

	public static String getYMDHSDay() {
		DateTime dt = new DateTime();
		return dt.plusDays(1).toString("yyyy-MM-dd HH:mm");
	}

	public static Date getNextDay() {
		DateTime dt = new DateTime();
		return dt.plusDays(1).toDate();
	}

	public static String getYMDDate() {
		DateTime dt = new DateTime();
		return dt.toString("yyyyMMdd");
	}

	public static String getYMDate() {
		DateTime dt = new DateTime();
		return dt.toString("yyyyMM");
	}

	public static String getYDate() {
		DateTime dt = new DateTime();
		return dt.toString("yyyy");
	}

	public static String getCurrentTime() {
		DateTime dt = new DateTime();
		return dt.toString("HH:mm:ss");
	}

	public static String getCurrentDate() {
		DateTime dt = new DateTime();
		return dt.toString("yyyy年MM月dd日 EE HH:mm:ss");
	}

	public static String nowDate(String pattern) {
		if (pattern == null) {
			pattern = "yyyy年MM月dd日 EE HH:mm:ss";
		}
		DateTime dt = new DateTime();
		return dt.toString(pattern);
	}

	public static String getYMDHMSDay() {
		DateTime dt = new DateTime();
		return dt.plusDays(1).toString("yyyyMMddHHmmss");
	}

	public static Date getDate(String date) {
		return new DateTime(date).toDate();
	}

	public static Date getDateByYMD(String ymd) {
		DateTime dt = new DateTime(ymd);
		return dt.toDate();
	}

	public static Date getDateByYMDHM(String ymdhm) {
		if ((StringUtils.isEmpty(ymdhm)) || (ymdhm.indexOf(" ") == -1)) {
			return null;
		}
		String[] reg = ymdhm.split(" ");
		String regex = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2]{1,2})-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
		if (!(reg[0].matches(regex))) {
			return null;
		}
		String regexhm = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9])$";
		if (!(reg[1].matches(regexhm))) {
			return null;
		}
		String[] ymd = reg[0].split("-");
		String[] hm = reg[1].split(":");
		DateTime dt = new DateTime(Integer.parseInt(ymd[0]),
				Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]),
				Integer.parseInt(hm[0]), Integer.parseInt(hm[1]), 0, 0);
		return dt.toDate();
	}

	public static Date getDateByYMDHMS(String ymdhms) {
		if ((StringUtils.isEmpty(ymdhms)) || (ymdhms.indexOf(" ") == -1)) {
			return null;
		}
		String[] reg = ymdhms.split(" ");
		String regex = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2]{1,2})-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
		if (!(reg[0].matches(regex))) {
			return null;
		}
		String regexhms = "^([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
		if (!(reg[1].matches(regexhms))) {
			return null;
		}
		String[] ymd = reg[0].split("-");
		String[] hms = reg[1].split(":");
		DateTime dt = new DateTime(Integer.parseInt(ymd[0]),
				Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]),
				Integer.parseInt(hms[0]), Integer.parseInt(hms[1]),
				Integer.parseInt(hms[2]), 0);
		return dt.toDate();
	}
}
