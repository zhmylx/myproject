package ins.framework.utils;

import ins.framework.Lang;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.BeanMap;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.LangUtils;

public class Https {
	static final int CONNECTION_TIMEOUT = 60000;
	static final int SO_TIMEOUT = 60000;
	static PoolingClientConnectionManager cm;

	public static void get(String url, HttpCallback httpCallback, Object data,
			Map<String, String> headers, Cookie[] cookies) {
		HttpClient httpclient = new DefaultHttpClient(cm);
		try {
			URIBuilder builder = new URIBuilder(url);
			Map dataMap = new BeanMap(data);

			for (Iterator i$ = dataMap.keySet().iterator(); i$.hasNext();) {
				Object key = i$.next();
				if (key == null) {
					continue;
				}
				Object value = dataMap.get(key);
				if (value == null) {
					continue;
				}
				builder.addParameter(key.toString(), value.toString());
			}

			CookieStore cookieStore = new BasicCookieStore();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}

			HttpContext localContext = new BasicHttpContext();

			localContext.setAttribute("http.cookie-store", cookieStore);
			HttpRequestBase httpRequest = new HttpGet(builder.build());
			httpRequest.getParams().setParameter("http.connection.timeout",
					Integer.valueOf(60000));

			httpRequest.getParams().setParameter("http.socket.timeout",
					Integer.valueOf(60000));

			if (headers != null) {
				for (String name : headers.keySet()) {
					httpRequest.setHeader(name, (String) headers.get(name));
				}
			}

			HttpResponse httpResponse = httpclient.execute(httpRequest,
					localContext);

			if (httpCallback != null)
				httpCallback.handle(httpResponse);
		} catch (Throwable e) {
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public static void download(String url, HttpCallback httpCallback,
			Object data, Map<String, String> headers, Cookie[] cookies) {
		HttpClient httpclient = new DefaultHttpClient(cm);
		try {
			URIBuilder builder = new URIBuilder(url);
			Map dataMap = new BeanMap(data);

			for (Iterator i$ = dataMap.keySet().iterator(); i$.hasNext();) {
				Object key = i$.next();
				if (key == null) {
					continue;
				}
				Object value = dataMap.get(key);
				if (value == null) {
					continue;
				}
				builder.addParameter(key.toString(), value.toString());
			}

			CookieStore cookieStore = new BasicCookieStore();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}

			HttpContext localContext = new BasicHttpContext();

			localContext.setAttribute("http.cookie-store", cookieStore);
			HttpRequestBase httpRequest = new HttpGet(builder.build());
			httpRequest.getParams().setParameter("http.connection.timeout",
					Integer.valueOf(60000));

			if (headers != null) {
				for (String name : headers.keySet()) {
					httpRequest.setHeader(name, (String) headers.get(name));
				}
			}

			HttpResponse httpResponse = httpclient.execute(httpRequest,
					localContext);

			if (httpCallback != null)
				httpCallback.handle(httpResponse);
		} catch (Throwable e) {
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public static void put(String url, HttpCallback httpCallback, Object data,
			Map<String, String> headers, Cookie[] cookies) {
		HttpClient httpclient = new DefaultHttpClient(cm);
		try {
			URIBuilder builder = new URIBuilder(url);
			Map dataMap = new BeanMap(data);

			for (Iterator i$ = dataMap.keySet().iterator(); i$.hasNext();) {
				Object key = i$.next();
				if (key == null) {
					continue;
				}
				Object value = dataMap.get(key);
				if (value == null) {
					continue;
				}
				builder.addParameter(key.toString(), value.toString());
			}

			CookieStore cookieStore = new BasicCookieStore();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}

			HttpContext localContext = new BasicHttpContext();

			localContext.setAttribute("http.cookie-store", cookieStore);
			HttpRequestBase httpRequest = new HttpPut(builder.build());
			httpRequest.getParams().setParameter("http.connection.timeout",
					Integer.valueOf(60000));

			httpRequest.getParams().setParameter("http.socket.timeout",
					Integer.valueOf(60000));

			if (headers != null) {
				for (String name : headers.keySet()) {
					httpRequest.setHeader(name, (String) headers.get(name));
				}
			}

			HttpResponse httpResponse = httpclient.execute(httpRequest,
					localContext);

			if (httpCallback != null)
				httpCallback.handle(httpResponse);
		} catch (Throwable e) {
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public static void delete(String url, HttpCallback httpCallback,
			Object data, Map<String, String> headers, Cookie[] cookies) {
		HttpClient httpclient = new DefaultHttpClient(cm);
		try {
			URIBuilder builder = new URIBuilder(url);
			Map dataMap = new BeanMap(data);

			for (Iterator i$ = dataMap.keySet().iterator(); i$.hasNext();) {
				Object key = i$.next();
				if (key == null) {
					continue;
				}
				Object value = dataMap.get(key);
				if (value == null) {
					continue;
				}
				builder.addParameter(key.toString(), value.toString());
			}

			CookieStore cookieStore = new BasicCookieStore();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}

			HttpContext localContext = new BasicHttpContext();

			localContext.setAttribute("http.cookie-store", cookieStore);
			HttpRequestBase httpRequest = new HttpDelete(builder.build());
			httpRequest.getParams().setParameter("http.connection.timeout",
					Integer.valueOf(60000));

			httpRequest.getParams().setParameter("http.socket.timeout",
					Integer.valueOf(60000));

			if (headers != null) {
				for (String name : headers.keySet()) {
					httpRequest.setHeader(name, (String) headers.get(name));
				}
			}

			HttpResponse httpResponse = httpclient.execute(httpRequest,
					localContext);

			if (httpCallback != null)
				httpCallback.handle(httpResponse);
		} catch (Throwable e) {
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public static void post(String url, HttpCallback httpCallback, Object data,
			Map<String, String> headers, Cookie[] cookies) {
		HttpClient httpclient = new DefaultHttpClient(cm);
		try {
			URIBuilder builder = new URIBuilder(url);

			CookieStore cookieStore = new BasicCookieStore();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}

			HttpContext localContext = new BasicHttpContext();

			localContext.setAttribute("http.cookie-store", cookieStore);
			HttpPost httpRequest = new HttpPost(builder.build());
			List formparams = new ArrayList();
			Map dataMap = new BeanMap(data);

			for (Iterator i$ = dataMap.keySet().iterator(); i$.hasNext();) {
				Object key = i$.next();
				if (key == null) {
					continue;
				}
				Object value = dataMap.get(key);
				if (value == null) {
					continue;
				}
				formparams.add(new BasicNameValuePair(key.toString(), value
						.toString()));
			}

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
					"UTF-8");

			httpRequest.setEntity(entity);
			httpRequest.getParams().setParameter("http.connection.timeout",
					Integer.valueOf(60000));

			httpRequest.getParams().setParameter("http.socket.timeout",
					Integer.valueOf(60000));

			if (headers != null) {
				for (String name : headers.keySet()) {
					httpRequest.setHeader(name, (String) headers.get(name));
				}
			}

			HttpResponse httpResponse = httpclient.execute(httpRequest,
					localContext);

			if (httpCallback != null)
				httpCallback.handle(httpResponse);
		} catch (Throwable e) {
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	public static void upload(String url, HttpCallback httpCallback, File file,
			Map<String, String> headers, Cookie[] cookies) {
		HttpClient httpclient = new DefaultHttpClient(cm);
		try {
			URIBuilder builder = new URIBuilder(url);

			CookieStore cookieStore = new BasicCookieStore();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
			}

			HttpContext localContext = new BasicHttpContext();

			localContext.setAttribute("http.cookie-store", cookieStore);
			HttpPost httpRequest = new HttpPost(builder.build());
			FileEntity entity = new FileEntity(file);
			httpRequest.setEntity(entity);
			httpRequest.getParams().setParameter("http.connection.timeout",
					Integer.valueOf(60000));

			httpRequest.getParams().setParameter("http.socket.timeout",
					Integer.valueOf(60000));

			if (headers != null) {
				for (String name : headers.keySet()) {
					httpRequest.setHeader(name, (String) headers.get(name));
				}
			}

			HttpResponse httpResponse = httpclient.execute(httpRequest,
					localContext);

			if (httpCallback != null)
				httpCallback.handle(httpResponse);
		} catch (Throwable e) {
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));

		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));

		cm = new PoolingClientConnectionManager(schemeRegistry);

		cm.setMaxTotal(1024);

		cm.setDefaultMaxPerRoute(512);
	}

	public static abstract interface HttpCallback {
		public abstract void handle(HttpResponse paramHttpResponse)
				throws Throwable;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.utils.Https JD-Core Version: 0.5.4
 */