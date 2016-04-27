package ins.framework.utils;

import ins.framework.Lang;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.WeakHashMap;

public final class Dates {
	private static String defaultDateFormatParthen = "yyyyMMddHHmmssSSS";
	private static ThreadLocal<DateFormat> defaultDateFormatTL = new ThreadLocal();
	private static ThreadLocal<Map<String, DateFormat>> dateFormatsTL = new ThreadLocal();

	public static Date getBeginOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 0);
		c.set(12, 0);
		c.set(13, 0);
		c.set(14, 0);
		return c.getTime();
	}

	public static Date getEndOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(11, 23);
		c.set(12, 59);
		c.set(13, 59);
		c.set(14, 999);
		return c.getTime();
	}

	public static Date toDate(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Date) {
			return new Date(((Date) o).getTime());
		}
		if (o instanceof Number) {
			return new Date(((Number) o).longValue());
		}
		String s = o.toString();
		s = s.replaceAll("\\D", "");
		if (s.length() < 8) {
			return null;
		}
		if (s.length() < defaultDateFormatParthen.length()) {
			StringBuilder sb = new StringBuilder(
					defaultDateFormatParthen.length());

			sb.append(s);
			while (sb.length() < defaultDateFormatParthen.length()) {
				sb.append('0');
			}
			s = sb.toString();
		} else {
			s = s.substring(0, defaultDateFormatParthen.length());
		}
		try {
			DateFormat defaultDateFormat = (DateFormat) defaultDateFormatTL
					.get();
			if (defaultDateFormat == null) {
				defaultDateFormat = new SimpleDateFormat(
						defaultDateFormatParthen);

				defaultDateFormatTL.set(defaultDateFormat);
			}
			return defaultDateFormat.parse(s);
		} catch (ParseException e) {
			throw Lang.wrapCause(e);
		}
	}

	public static String toString(Date date, String format) {
		Map dateFormats = (Map) dateFormatsTL.get();
		if (dateFormats == null) {
			dateFormats = new WeakHashMap();
			dateFormatsTL.set(dateFormats);
		}
		format = format.trim();
		DateFormat dateFormat = (DateFormat) dateFormats.get(format);
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(format);
			dateFormats.put(format, dateFormat);
		}
		return dateFormat.format(date);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.utils.Dates JD-Core Version: 0.5.4
 */