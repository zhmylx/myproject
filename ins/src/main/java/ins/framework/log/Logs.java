package ins.framework.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;
import sun.reflect.Reflection;

public class Logs {
	private static final Object[] EMPTY_ARRAY = new Object[0];
	private static final String FQCN = Logs.class.getName();

	private static LocationAwareLogger getLocationAwareLogger(int depth) {
		String className = Reflection.getCallerClass(depth).getName();

		return (LocationAwareLogger) LoggerFactory.getLogger(className);
	}

	public static Logger getLogger() {
		return getLocationAwareLogger(3);
	}

	public static String getName() {
		return getLocationAwareLogger(3).getName();
	}

	public static boolean isTraceEnabled() {
		return getLocationAwareLogger(3).isTraceEnabled();
	}

	public static void trace(String msg) {
		getLocationAwareLogger(3).log(null, FQCN, 0, msg, EMPTY_ARRAY, null);
	}

	public static void trace(String format, Object arg) {
		getLocationAwareLogger(3).log(null, FQCN, 0, format,
				new Object[] { arg }, null);
	}

	public static void trace(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(3).log(null, FQCN, 0, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void trace(String format, Object[] arguments) {
		getLocationAwareLogger(3).log(null, FQCN, 0, format, arguments, null);
	}

	public static void trace(String msg, Throwable e) {
		getLocationAwareLogger(3).log(null, FQCN, 0, msg, EMPTY_ARRAY, e);
	}

	public static boolean isTraceEnabled(Marker marker) {
		return getLocationAwareLogger(3).isTraceEnabled(marker);
	}

	public static void trace(Marker marker, String msg) {
		getLocationAwareLogger(3).log(marker, FQCN, 0, msg, EMPTY_ARRAY, null);
	}

	public static void trace(Marker marker, String format, Object arg) {
		getLocationAwareLogger(3).log(marker, FQCN, 0, format,
				new Object[] { arg }, null);
	}

	public static void trace(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(3).log(marker, FQCN, 0, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void trace(Marker marker, String format, Object[] argArray) {
		getLocationAwareLogger(3).log(marker, FQCN, 0, format, argArray, null);
	}

	public static void trace(Marker marker, String msg, Throwable e) {
		getLocationAwareLogger(3).log(marker, FQCN, 0, msg, EMPTY_ARRAY, e);
	}

	public static boolean isInfoEnabled() {
		return getLocationAwareLogger(3).isInfoEnabled();
	}

	public static void info(String msg) {
		getLocationAwareLogger(3).log(null, FQCN, 20, msg, EMPTY_ARRAY, null);
	}

	public static void info(String format, Object arg) {
		getLocationAwareLogger(3).log(null, FQCN, 20, format,
				new Object[] { arg }, null);
	}

	public static void info(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(3).log(null, FQCN, 20, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void info(String format, Object[] arguments) {
		getLocationAwareLogger(3).log(null, FQCN, 20, format, arguments, null);
	}

	public static void info(String msg, Throwable e) {
		getLocationAwareLogger(3).log(null, FQCN, 20, msg, EMPTY_ARRAY, e);
	}

	public static boolean isInfoEnabled(Marker marker) {
		return getLocationAwareLogger(3).isInfoEnabled(marker);
	}

	public static void info(Marker marker, String msg) {
		getLocationAwareLogger(3).log(marker, FQCN, 20, msg, EMPTY_ARRAY, null);
	}

	public static void info(Marker marker, String format, Object arg) {
		getLocationAwareLogger(3).log(marker, FQCN, 20, format,
				new Object[] { arg }, null);
	}

	public static void info(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(3).log(marker, FQCN, 20, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void info(Marker marker, String format, Object[] argArray) {
		getLocationAwareLogger(3).log(marker, FQCN, 20, format, argArray, null);
	}

	public static void info(Marker marker, String msg, Throwable e) {
		getLocationAwareLogger(3).log(marker, FQCN, 20, msg, EMPTY_ARRAY, e);
	}

	public static boolean isDebugEnabled() {
		return getLocationAwareLogger(3).isDebugEnabled();
	}

	public static void debug(String msg) {
		getLocationAwareLogger(3).log(null, FQCN, 10, msg, EMPTY_ARRAY, null);
	}

	public static void debug(String format, Object arg) {
		getLocationAwareLogger(3).log(null, FQCN, 10, format,
				new Object[] { arg }, null);
	}

	public static void debug(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(3).log(null, FQCN, 10, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void debug(String format, Object[] arguments) {
		getLocationAwareLogger(3).log(null, FQCN, 10, format, arguments, null);
	}

	public static void debug(String msg, Throwable e) {
		getLocationAwareLogger(3).log(null, FQCN, 10, msg, EMPTY_ARRAY, e);
	}

	public static boolean isDebugEnabled(Marker marker) {
		return getLocationAwareLogger(3).isDebugEnabled(marker);
	}

	public static void debug(Marker marker, String msg) {
		getLocationAwareLogger(3).log(marker, FQCN, 10, msg, EMPTY_ARRAY, null);
	}

	public static void debug(Marker marker, String format, Object arg) {
		getLocationAwareLogger(3).log(marker, FQCN, 10, format,
				new Object[] { arg }, null);
	}

	public static void debug(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(3).log(marker, FQCN, 10, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void debug(Marker marker, String format, Object[] argArray) {
		getLocationAwareLogger(3).log(marker, FQCN, 10, format, argArray, null);
	}

	public static void debug(Marker marker, String msg, Throwable e) {
		getLocationAwareLogger(3).log(marker, FQCN, 10, msg, EMPTY_ARRAY, e);
	}

	public static boolean isWarnEnabled() {
		return getLocationAwareLogger(3).isWarnEnabled();
	}

	public static void warn(String msg) {
		getLocationAwareLogger(3).log(null, FQCN, 30, msg, EMPTY_ARRAY, null);
	}

	public static void warn(String format, Object arg) {
		getLocationAwareLogger(3).log(null, FQCN, 30, format,
				new Object[] { arg }, null);
	}

	public static void warn(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(3).log(null, FQCN, 30, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void warn(String format, Object[] arguments) {
		getLocationAwareLogger(3).log(null, FQCN, 30, format, arguments, null);
	}

	public static void warn(String msg, Throwable e) {
		getLocationAwareLogger(3).log(null, FQCN, 30, msg, EMPTY_ARRAY, e);
	}

	public static boolean isWarnEnabled(Marker marker) {
		return getLocationAwareLogger(3).isWarnEnabled(marker);
	}

	public static void warn(Marker marker, String msg) {
		getLocationAwareLogger(3).log(marker, FQCN, 30, msg, EMPTY_ARRAY, null);
	}

	public static void warn(Marker marker, String format, Object arg) {
		getLocationAwareLogger(3).log(marker, FQCN, 30, format,
				new Object[] { arg }, null);
	}

	public static void warn(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(3).log(marker, FQCN, 30, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void warn(Marker marker, String format, Object[] argArray) {
		getLocationAwareLogger(3).log(marker, FQCN, 30, format, argArray, null);
	}

	public static void warn(Marker marker, String msg, Throwable e) {
		getLocationAwareLogger(3).log(marker, FQCN, 30, msg, EMPTY_ARRAY, e);
	}

	public static boolean isErrorEnabled() {
		return getLocationAwareLogger(3).isErrorEnabled();
	}

	public static void error(String msg) {
		getLocationAwareLogger(3).log(null, FQCN, 40, msg, EMPTY_ARRAY, null);
	}

	public static void error(String format, Object arg) {
		getLocationAwareLogger(3).log(null, FQCN, 40, format,
				new Object[] { arg }, null);
	}

	public static void error(String format, Object arg1, Object arg2) {
		getLocationAwareLogger(3).log(null, FQCN, 40, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void error(String format, Object[] arguments) {
		getLocationAwareLogger(3).log(null, FQCN, 40, format, arguments, null);
	}

	public static void error(String msg, Throwable e) {
		getLocationAwareLogger(3).log(null, FQCN, 40, msg, EMPTY_ARRAY, e);
	}

	public static boolean isErrorEnabled(Marker marker) {
		return getLocationAwareLogger(3).isErrorEnabled(marker);
	}

	public static void error(Marker marker, String msg) {
		getLocationAwareLogger(3).log(marker, FQCN, 40, msg, EMPTY_ARRAY, null);
	}

	public static void error(Marker marker, String format, Object arg) {
		getLocationAwareLogger(3).log(marker, FQCN, 40, format,
				new Object[] { arg }, null);
	}

	public static void error(Marker marker, String format, Object arg1,
			Object arg2) {
		getLocationAwareLogger(3).log(marker, FQCN, 40, format,
				new Object[] { arg1, arg2 }, null);
	}

	public static void error(Marker marker, String format, Object[] argArray) {
		getLocationAwareLogger(3).log(marker, FQCN, 40, format, argArray, null);
	}

	public static void error(Marker marker, String msg, Throwable e) {
		getLocationAwareLogger(3).log(marker, FQCN, 40, msg, EMPTY_ARRAY, e);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.log.Logs JD-Core Version: 0.5.4
 */