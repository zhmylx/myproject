package utils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * String 工具类
 * @author Homing Tsang
 *
 * 2015年11月1日
 */
public class StringUtil {

	private static final String CONTRACT = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 
	 * getRandomString(生成指定长度的随机字符)
	 * 
	 * @param length  生成随机字符的长度
	 * @return
	 * 
	 */
	public String getRandomString(Integer length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(CONTRACT.charAt(random.nextInt(CONTRACT.length())));
		}
		return sb.toString();
	}

	/**
	 * 
	 * getRaString(生成一个长度为20的随机字符)
	 * 
	 * @return
	 */
	public String getRandomString() {
		return getRandomString(20);
	}

	/**
	 * 
	 * getUUIDString(生成UUID)
	 * 
	 * @return
	 * 
	 */
	public synchronized static String getUUIDString() {
		String id = UUID.randomUUID().toString();
		id = id.replace("-", "");
		return id;
	}

	/**
	 * 
	 * getIpAddr(获取IP)
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!org.apache.commons.lang.StringUtils.isBlank(ip)
				&& !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!org.apache.commons.lang.StringUtils.isBlank(ip)
				&& !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

	/**
	 * 创建list
	 * 
	 * @param parameter
	 * @param Collection
	 */
	public static void paresToList(String parameter,
			Collection<String> container, String delim) {
		if (null == parameter) {
			return;
		}
		StringTokenizer st = new StringTokenizer(parameter, delim);
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			if (token.length() > 0) {
				container.add(token);
			}
		}
	}

	/**
	 * 指定字符 分割 返回数组
	 * 
	 * @param parameter
	 * @param Collection
	 */
	public static String[] paresToArray(String parameter, String delim) {
		if (!hasLength(parameter)) {
			return null;
		}
		List<String> container = new ArrayList<String>();
		paresToList(parameter, container, delim);
		return (container.toArray(new String[container.size()]));
	}

	/**
	 * 指定字符 分割并去掉重复的数据 返回数组
	 * 
	 * @param parameter
	 * @param Collection
	 */
	public static String[] paresDuplicateRemovalToArray(String parameter,
			String delim) {
		if (!hasLength(parameter)) {
			return null;
		}
		Set<String> container = new HashSet<String>();
		paresToList(parameter, container, delim);
		return (container.toArray(new String[container.size()]));
	}

	/**
	 * 判断字符是否为空<br>
	 * StringUtils.hasLength(null) = false<br>
	 * StringUtils.hasLength("") = false<br>
	 * StringUtils.hasLength(" ") = true<br>
	 * StringUtils.hasLength("Hello") = true<br>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	/**
	 * 判断字符数组是否为空<br>
	 * StringUtils.hasLength(null) = false<br>
	 * StringUtils.hasLength({}) = false<br>
	 * StringUtils.hasLength({"Hello"}) = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(String[] str) {
		return hasLength((CharSequence[]) str);
	}

	/**
	 * StringUtils.hasLength(null) = false StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true StringUtils.hasLength("Hello") = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * StringUtils.hasLength(null) = false StringUtils.hasLength({}) = false
	 * StringUtils.hasLength({"Hello"}) = true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence[] str) {
		return (str != null && str.length > 0);
	}

	/**
	 * 合并数组
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * 转换集合成数组
	 * 
	 * @param collection
	 * @return
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * 数组转成字符串
	 * 
	 * @param arr
	 * @param delim
	 * @return
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * 指定数组 中是否有元素是以 指定 字符串 开始
	 * 
	 * @param sArray
	 * @param value
	 * @return
	 */
	public static boolean startsWithStringArray(String[] sArray, String value) {
		for (int i = 0; i < sArray.length; i++) {
			String v = sArray[i];
			if (value.startsWith(v)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串 编码成 Unicode
	 * 
	 * @param string
	 * @return
	 */
	public static String encodeUnicode(final String string) {
		char[] utfBytes = string.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			sb.append("\\u" + hexB);
		}
		return sb.toString();
	}

	/**
	 * 解码 Unicode成string
	 * 
	 * @param string
	 * @return
	 */
	public static String decodeUnicode(String string) {
		int n = string.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = string.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * 日志描述
	 * 
	 * @param request
	 * @param strings
	 */
	public static String requestActiongLogging(String[] strings, Integer state)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		String r = (state == null || state == 1) ? "成功" : "失败";
		if (strings.length == 3) {
			sb.append("新增 ");
			sb.append(strings[0]);
			sb.append("【");
			sb.append(strings[1]);
			sb.append("】的记录，结果是【");
			sb.append(strings[2]);
			sb.append(r);
			sb.append("】");
		} else if (strings.length == 4) {
			sb.append("对");
			sb.append(strings[0]);
			sb.append("【");
			sb.append(strings[1]);
			sb.append("】的记录，进行【");
			sb.append(strings[2]);
			sb.append("】，结果是【");
			sb.append(strings[3]);
			sb.append(r);
			sb.append("】");
		}
		return sb.toString();

	}

	public static boolean isNotNull(String args) {
		if (args == null)
			return false;
		if ("".equals(args.trim()))
			return false;
		return true;
	}

	public static boolean isNotNull(Long args) {
		if (args == null)
			return false;
		return true;
	}

	public static boolean isNotNull(Double args) {
		if (args == null)
			return false;
		return true;
	}

	public static boolean isNotNull(Date args) {
		if (args == null)
			return false;
		return true;
	}

	public static boolean checkEmail(String email) {
		if (!isNotNull(email)) {
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
		return (String[]) result.toArray(new String[result.size()]);
	}

}
