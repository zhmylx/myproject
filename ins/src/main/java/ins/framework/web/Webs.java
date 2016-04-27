package ins.framework.web;

import com.opensymphony.xwork2.ActionContext;
import ins.framework.Lang;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Webs {
	static ThreadLocal<HttpServletRequest> REQUEST_TL = new ThreadLocal();

	static ThreadLocal<HttpServletResponse> RESPONSE_TL = new ThreadLocal();

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((Lang.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((Lang.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((Lang.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}

		String[] ss = ip.split(",");
		for (String s : ss) {
			if (!"unknown".equalsIgnoreCase(s)) {
				ip = s;
				break;
			}
		}
		return ip;
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) REQUEST_TL.get();
		if (request == null) {
			try {
				ActionContext ctx = ActionContext.getContext();
				if (ctx != null) {
					request = (HttpServletRequest) ctx
							.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");

					if (request != null)
						REQUEST_TL.set(request);
				}
			} catch (Throwable e) {
			}
		}
		if (request == null) {
			try {
				request = ((ServletRequestAttributes) RequestContextHolder
						.getRequestAttributes()).getRequest();

				if (request != null)
					REQUEST_TL.set(request);
			} catch (Throwable e) {
			}
		}
		return request;
	}

	public static HttpServletResponse getResponse() {
		HttpServletResponse response = (HttpServletResponse) RESPONSE_TL.get();
		if (response == null) {
			try {
				ActionContext ctx = ActionContext.getContext();
				if (ctx != null) {
					response = (HttpServletResponse) ctx
							.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");

					if (response != null)
						RESPONSE_TL.set(response);
				}
			} catch (Throwable e) {
			}
		}
		return response;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.web.Webs JD-Core Version: 0.5.4
 */