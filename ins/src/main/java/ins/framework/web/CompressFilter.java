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

public class CompressFilter implements Filter {
	private static final String GZIP_FILTER = "sinosoft.servlet.gzip.filter";
	private String[] ignoreKeys;
	private int ignoreKeyCount;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if ((!(res instanceof HttpServletResponse))
				|| (!(req instanceof HttpServletRequest))) {
			chain.doFilter(req, res);
			return;
		}

		if (req.getAttribute("sinosoft.servlet.gzip.filter") != null) {
			chain.doFilter(req, res);
			return;
		}
		req.setAttribute("sinosoft.servlet.gzip.filter", "true");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		boolean flag = true;
		String ae = request.getHeader("Accept-Encoding");
		if ((ae == null) || (ae.indexOf("gzip") == -1)) {
			flag = false;
		}
		if (flag) {
			String uri = request.getRequestURI();
			if (uri == null) {
				uri = "";
			}
			uri = uri.toLowerCase();
			for (int i = 0; i < this.ignoreKeyCount; ++i) {
				if (uri.endsWith(this.ignoreKeys[i])) {
					flag = false;
					break;
				}
			}
		}
		if (!flag) {
			chain.doFilter(req, res);
			return;
		}
		GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
		try {
			chain.doFilter(req, wrappedResponse);
		} finally {
			wrappedResponse.finishResponse();
		}
	}

	public void init(FilterConfig filterConfig) {
		String ignoreKey = filterConfig.getInitParameter("ignoreKey");
		if (ignoreKey != null)
			this.ignoreKeys = ignoreKey.split(",");
		else {
			this.ignoreKeys = new String[0];
		}
		this.ignoreKeyCount = this.ignoreKeys.length;
	}

	public void destroy() {
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.web.CompressFilter JD-Core Version: 0.5.4
 */