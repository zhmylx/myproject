package ins.framework.security;

import ins.framework.Lang;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import org.apache.commons.lang3.Validate;

public class Digests {
	public static final String SHA1 = "SHA-1";
	public static final String SHA224 = "SHA-224";
	public static final String SHA256 = "SHA-256";
	public static final String SHA384 = "SHA-384";
	public static final String SHA512 = "SHA-512";
	public static final String MD5 = "MD5";
	private static SecureRandom random = new SecureRandom();

	public static byte[] sha1(byte[] input) {
		return digest(input, "SHA-1", null, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt) {
		return digest(input, "SHA-1", salt, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, "SHA-1", salt, iterations);
	}

	private static byte[] digest(byte[] input, String algorithm, byte[] salt,
			int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; ++i) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw Lang.wrapCause(e);
		}
	}

	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0,
				"numBytes argument must be a positive integer (1 or larger)",
				numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	public static byte[] md5(InputStream input) throws IOException {
		return digest(input, "MD5");
	}

	public static byte[] sha1(InputStream input) throws IOException {
		return digest(input, "SHA-1");
	}

	private static byte[] digest(InputStream input, String algorithm)
			throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8192;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			throw Lang.wrapCause(e);
		}
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.security.Digests JD-Core Version: 0.5.4
 */