package utils.web.struts2;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import utils.web.ServletUtils;
import utils.web.WebUtil;

public abstract class Struts2Utils {
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	public static final <T> T json2Object(String name, Class<T> clazz) {
		String mp = getParameter(name);
		return JSON.parseObject(mp, clazz);
	}

	public static final <T> T formJson2Object(Class<T> clazz) {
		String mp = getParameter("submitData");
		return JSON.parseObject(mp, clazz);
	}

	public static final <T> T requestParam2Bean(T bean) {
		return WebUtil.requestParam2Bean(getRequest(), bean);
	}

	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public static HttpSession getSession(boolean isNew) {
		return ServletActionContext.getRequest().getSession(isNew);
	}

	public static void setRequestParameters() {
		ServletUtils.setParameterStartingWith(getRequest(), "filter_");
	}

	public static Object getSessionAttribute(String name) {
		HttpSession session = getSession(false);
		return ((session != null) ? session.getAttribute(name) : null);
	}

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	public static Map<String, Object> getParametersStarting() {
		return ServletUtils.getParametersStartingWith(getRequest(), "");
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static void render(String contentType, String content,
			String[] headers) {
		HttpServletResponse response = initResponseHeader(contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void renderText(String text, String[] headers) {
		render("text/plain", text, headers);
	}

	public static void renderHtml(String html, String[] headers) {
		render("text/html", html, headers);
	}

	public static void renderXml(String xml, String[] headers) {
		render("text/xml", xml, headers);
	}

	public static void renderJson(String jsonString, String[] headers) {
		render("application/json", jsonString, headers);
	}

	public static void renderJson(Object data, String[] headers) {
		HttpServletResponse response = initResponseHeader("application/json",
				headers);
		try {
			String json = JSON.toJSONString(data);
			response.getWriter().write(json);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void renderJsonp(String callbackName, Object object,
			String[] headers) {
		String jsonString = JSON.toJSONString(object);
		String result = callbackName + "(" + jsonString + ");";

		render("text/javascript", result, headers);
	}

	private static HttpServletResponse initResponseHeader(String contentType,
			String[] headers) {
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

		HttpServletResponse response = ServletActionContext.getResponse();

		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setDisableCacheHeader(response);
		}

		return response;
	}
}
