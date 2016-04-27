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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CacheFilter implements Filter {
	private static final Log logger = LogFactory.getLog(CacheFilter.class);
	private int time;

	public CacheFilter() {
		this.time = 0;
	}

	public void init(FilterConfig filterConfig) {
		String expireTime = filterConfig.getInitParameter("expireTime");
		if (expireTime == null)
			return;
		try {
			this.time = Integer.parseInt(expireTime);
		} catch (NumberFormatException e) {
			logger.warn("init parameter expireTime must be a int");
			this.time = 0;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (this.time > 0) {
			res.setHeader("Cache-Control", "max-age=" + this.time
					+ ",s-maxage=" + this.time);

			if (logger.isDebugEnabled()) {
				logger.debug("set max-age for " + req.getRequestURI());
			}
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.web.CacheFilter JD-Core Version: 0.5.4
 */