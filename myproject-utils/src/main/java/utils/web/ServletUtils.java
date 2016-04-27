package utils.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import utils.AssertUtils;
import utils.EncodeUtils;
import utils.web.struts2.Struts2Utils;


public abstract class ServletUtils {
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String TEXT_TYPE = "text/plain";
	public static final String AUTHENTICATION_HEADER = "Authorization";
	public static final long ONE_YEAR_SECONDS = 31536000L;
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000L);

		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	public static void setDisableCacheHeader(HttpServletResponse response) {
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");

		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1L)
				&& (lastModified < ifModifiedSince + 1000L)) {
			response.setStatus(304);
			return false;
		}
		return true;
	}

	

	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			String encodedfileName = new String(fileName.getBytes(),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
	}

	public static void setParameterStartingWith(ServletRequest request,
			String perfix) {
		Map<String, Object> filterParamMap = getParametersStartingWith(
				Struts2Utils.getRequest(), "filter_");
		for (Map.Entry entry : filterParamMap.entrySet())
			request.setAttribute("filter_" + ((String) entry.getKey()),
					entry.getValue());
	}

	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefix) {
		AssertUtils.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		if (prefix == null) {
			prefix = "";
		}
		while ((paramNames != null) && (paramNames.hasMoreElements())) {
			String paramName = (String) paramNames.nextElement();
			if (("".equals(prefix)) || (paramName.startsWith(prefix))) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if ((values == null) || (values.length == 0))
					continue;
				if (values.length > 1)
					params.put(unprefixed, values);
				else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	public static String encodeHttpBasic(String userName, String password) {
		String encode = userName + ":" + password;
		return "Basic " + EncodeUtils.base64Encode(encode.getBytes());
	}

	private static HttpServletResponse initResponseHeader(
			HttpServletResponse response, String contentType, String[] headers) {
		String encoding = "UTF-8";
		boolean noCache = true;
		for (String header : headers) {
			String headerName = StringUtils.substringBefore(header, ":");
			String headerValue = StringUtils.substringAfter(header, ":");

			if (StringUtils.equalsIgnoreCase(headerName, "encoding"))
				encoding = headerValue;
			else if (StringUtils.equalsIgnoreCase(headerName, "no-cache"))
				noCache = Boolean.parseBoolean(headerValue);
			else {
				throw new IllegalArgumentException(headerName
						+ "不是一个合法的header类型");
			}
		}

		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			setDisableCacheHeader(response);
		}
		return response;
	}

	public static void render(HttpServletResponse response, String contentType,
			String content, String[] headers) {
		response = initResponseHeader(response, contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}

