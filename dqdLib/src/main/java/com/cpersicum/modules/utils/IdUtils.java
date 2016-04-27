package com.cpersicum.modules.utils;

import java.security.SecureRandom;
import java.util.UUID;

public abstract class IdUtils {
	private static SecureRandom random = new SecureRandom();

	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	public static long randomLong() {
		return random.nextLong();
	}

	public static String randomBase62() {
		return EncodeUtils.base62Encode(random.nextLong());
	}

	public static Long creditscore() {
		return Long.valueOf(10L);
	}

	public static Long totalpoints() {
		return Long.valueOf(1000L);
	}

	public static Long points() {
		return Long.valueOf(1000L);
	}
}
