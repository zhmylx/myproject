package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

public abstract class EncodeUtils {
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	public static String base64Encode(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	public static String base36Encode(long num) {
		return alphabetEncode(num, 36);
	}

	public static long base36Decode(String str) {
		return alphabetDecode(str, 36);
	}

	public static String base62Encode(long num) {
		return alphabetEncode(num, 62);
	}

	public static long base62Decode(String str) {
		return alphabetDecode(str, 62);
	}

	private static String alphabetEncode(long num, int base) {
		num = Math.abs(num);
		StringBuilder sb = new StringBuilder();
		for (; num > 0L; num /= base) {
			sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
					.charAt((int) (num % base)));
		}

		return sb.toString();
	}

	private static long alphabetDecode(String str, int base) {
		AssertUtils.hasText(str);

		long result = 0L;
		for (int i = 0; i < str.length(); i++) {
			result = (long) (result + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
					.indexOf(str.charAt(i)) * Math.pow(base, i));
		}

		return result;
	}

	public static String urlEncode(String input) {
		try {
			return URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported Encoding Exception", e);
		}
	}

	public static String urlDecode(String input) {
		try {
			return URLDecoder.decode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported Encoding Exception", e);
		}
	}

	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml3(html);
	}

	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml3(htmlEscaped);
	}

	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
}
