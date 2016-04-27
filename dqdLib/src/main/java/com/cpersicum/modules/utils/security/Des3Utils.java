package com.cpersicum.modules.utils.security;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Des3Utils {
	private static final String GBK = "GBK";
	private static final String SPKEY = "1234";

	public static String get3DESEncrypt(String src, String spkey) {
		String requestValue = "";
		try {
			byte[] enKey = getEnKey(spkey);

			byte[] src2 = src.getBytes("UTF-16LE");

			byte[] encryptedData = Encrypt(src2, enKey);

			String base64String = getBase64Encode(encryptedData);

			String base64Encrypt = filter(base64String);

			requestValue = getURLEncode(base64Encrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestValue;
	}

	public static String getBase64Encode(byte[] src) {
		String requestValue = "";
		try {
			BASE64Encoder base64en = new BASE64Encoder();
			requestValue = base64en.encode(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestValue;
	}

	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); ++i) {
			int asc = str.charAt(i);
			if ((asc != 10) && (asc != 13))
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}

	public static byte[] Encrypt(byte[] src, byte[] enKey) {
		byte[] encryptedData = (byte[]) null;
		try {
			DESedeKeySpec dks = new DESedeKeySpec(enKey);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey key = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(1, key);
			encryptedData = cipher.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedData;
	}

	public static String getURLEncode(String src) {
		String requestValue = "";
		try {
			requestValue = URLEncoder.encode(src, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestValue;
	}

	public static String get3DESDecrypt(String src, String spkey) {
		String requestValue = "";
		try {
			String URLValue = getURLDecoderdecode(src);

			BASE64Decoder base64Decode = new BASE64Decoder();
			byte[] base64DValue = base64Decode.decodeBuffer(URLValue);

			requestValue = deCrypt(base64DValue, spkey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestValue;
	}

	public static String getURLDecoderdecode(String src) {
		String requestValue = "";
		try {
			requestValue = URLDecoder.decode(src, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return requestValue;
	}

	public static String deCrypt(byte[] debase64, String spKey) {
		String strDe = null;
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("DESede");
			byte[] key = getEnKey(spKey);
			DESedeKeySpec dks = new DESedeKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey sKey = keyFactory.generateSecret(dks);
			cipher.init(2, sKey);
			byte[] ciphertext = cipher.doFinal(debase64);
			strDe = new String(ciphertext, "UTF-16LE");
		} catch (Exception ex) {
			strDe = "";
			ex.printStackTrace();
		}
		return strDe;
	}

	private static byte[] getEnKey(String spKey) {
		byte[] desKey = (byte[]) null;
		try {
			byte[] desKey1 = md5(spKey);
			desKey = new byte[24];
			int i = 0;
			while ((i < desKey1.length) && (i < 24)) {
				desKey[i] = desKey1[i];
				++i;
			}
			if (i < 24) {
				desKey[i] = 0;
				++i;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return desKey;
	}

	private static byte[] md5(String strSrc) {
		byte[] returnByte = (byte[]) null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			returnByte = md5.digest(strSrc.getBytes("GBK"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnByte;
	}

	public static String encrypt(String pwd) {
		return get3DESEncrypt(pwd, "1234");
	}

	public static String decrypt(String pwd) {
		return get3DESDecrypt(pwd, "1234");
	}

	public static void main(String[] args) {
		String oldString = "12 33";

		System.out.println("1。分配SPKEY为:  1234");
		System.out.println("2。加密内容为:  " + oldString);
		String reValue = get3DESEncrypt(oldString, "1234");
		reValue = reValue.trim().intern();
		System.out.println("进行3-DES加密后的内容: " + reValue);
		System.out.println("Szs6Iwmn3QKg2arQpLdvqQ%3D%3D".equals(reValue));
		String reValue2 = get3DESDecrypt(reValue, "1234");
		System.out.println("进行3-DES解密后的内容: " + reValue2);
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.utils.security.Des3Utils JD-Core Version: 0.5.3
 */