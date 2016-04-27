package com.cpersicum.modules.utils.security;

import com.cpersicum.modules.utils.EncodeUtils;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public abstract class DigestUtils {
	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	public static String sha1ToHex(String input) {
		byte[] digestResult = digest(input, "SHA-1");
		return EncodeUtils.hexEncode(digestResult);
	}

	public static String sha1ToBase64(String input) {
		byte[] digestResult = digest(input, "SHA-1");
		return EncodeUtils.base64Encode(digestResult);
	}

	public static String sha1ToBase64UrlSafe(String input) {
		byte[] digestResult = digest(input, "SHA-1");
		return EncodeUtils.base64UrlSafeEncode(digestResult);
	}

	private static byte[] digest(String input, String algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			return messageDigest.digest(input.getBytes());
		} catch (GeneralSecurityException e) {
			throw convertRuntimeException(e);
		}
	}

	public static String md5ToUrlSafeEncode(String input) {
		byte[] digestResult = digest(input, "MD5");
		return EncodeUtils.base64UrlSafeEncode(digestResult);
	}

	public static String md5ToHex(InputStream input) throws IOException {
		return digest(input, "MD5");
	}

	public static String sha1ToHex(InputStream input) throws IOException {
		return digest(input, "SHA-1");
	}

	private static String digest(InputStream input, String algorithm)
			throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return EncodeUtils.hexEncode(messageDigest.digest());
		} catch (GeneralSecurityException e) {
			throw convertRuntimeException(e);
		}
	}

	private static RuntimeException convertRuntimeException(
			GeneralSecurityException e) {
		return new RuntimeException("Digest exception", e);
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.utils.security.DigestUtils JD-Core Version: 0.5.3
 */