package ins.framework.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Webs.REQUEST_TL.set((HttpServletRequest) request);
		Webs.RESPONSE_TL.set((HttpServletResponse) response);
		try {
			chain.doFilter(request, response);
		} finally {
			Webs.REQUEST_TL.remove();
			Webs.RESPONSE_TL.remove();
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.web.WebFilter JD-Core Version: 0.5.4
 */