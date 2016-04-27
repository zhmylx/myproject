package com.cpersicum.modules.utils;

import java.util.Date;

public class CommUtils {
	public static boolean isNull(Object value) {
		boolean flag = false;
		if ((value instanceof String) && (!("".equals(value)))
				&& (value != null)) {
			flag = true;
		}

		if ((value instanceof Integer) && (value != null)
				&& (Integer.parseInt(value.toString()) != 0)) {
			flag = true;
		}

		if ((value instanceof Long) && (value != null)
				&& (Long.parseLong(value.toString()) != 0L)) {
			flag = true;
		}

		if ((value instanceof Date) && (value != null)) {
			flag = true;
		}

		return flag;
	}
}
