package com.cpersicum.modules.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Exceptions {
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return ((RuntimeException) e);
		}
		return new RuntimeException(e);
	}

	public static String getStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}
