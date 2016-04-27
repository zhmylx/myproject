package com.cpersicum.modules.utils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isNotNull(String args) {
		if (args == null) {
			return false;
		}
		return (!("".equals(args.trim())));
	}

	public static boolean isNotNull(Long args) {
		return (args != null);
	}

	public static boolean isNotNull(Double args) {
		return (args != null);
	}

	public static boolean isNotNull(Date args) {
		return (args != null);
	}

	public static boolean checkEmail(String email) {
		if (!(isNotNull(email))) {
			return false;
		}

		String regex = "^\\w+(.)*@\\w+\\.(com\\.cn)|\\w+(.)*@\\w+\\.(com|cn)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

	public static String[] split(String str, String seperators) {
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return ((String[]) result.toArray(new String[result.size()]));
	}

	public static void main(String[] args) {
		System.out.println(checkEmail("han.xiaojun@zte.com.cn"));
		System.out.println(checkEmail("lih_dodo@126.com"));
		System.out.println(checkEmail("wolihua2005@sina."));
		System.out.println(checkEmail("_wolihua2005@sina.com.cn"));
		System.out.println(checkEmail("wo--lihua2005@sina.com.cn"));
	}
}
