package ins.framework.utils;

import ins.framework.web.Webs;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

public class Envs {
	private static String SERVER_IP = null;

	public static String getClientIp(HttpServletRequest request) {
		return Webs.getIpAddress(request);
	}

	public static String getWebClassesPath() {
		String path = new File(Thread.currentThread().getContextClassLoader()
				.getResource("").getPath()).getPath();

		path = path.replace("%20", " ");
		return path;
	}

	public static String getServerIp() {
		return SERVER_IP;
	}

	public static void main(String[] args) {
		System.out.println("IP=[" + getServerIp() + "]");
		System.out.println(getWebClassesPath());
	}

	private static String getInnerServerIp() {
		String osName = System.getProperty("os.name").toLowerCase();
		String ip = null;
		try {
			if (osName.startsWith("linux")) {
				ip = getLocalIp("/sbin/ip addr", "inet ", "", "/");
			} else if (osName.startsWith("window")) {
				ip = getLocalIp("ipconfig /all", "IP Address", ": ", "(");
				if (ip == null)
					ip = getLocalIp("ipconfig /all", "IPv4 地址", ":", "(");
			} else if (osName.startsWith("aix")) {
				ip = getLocalIp("ifconfig -a", "inet ", "", "netmask");
			} else {
				throw new IllegalStateException("Not support OS:" + osName);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e.toString(), e);
		}
		if (ip == null) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	private static String getLocalIp(String cmd, String indLine,
			String indStart, String indEnd) throws Exception {
		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec(cmd);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String s;
		while ((s = in.readLine()) != null) {
			s = s.trim();
			if (s.startsWith(indLine))
				;
			s = s.substring(indLine.length()).trim();
			int pos = s.indexOf(indStart);
			String ip = s.substring(indStart.length() + pos).trim();
			pos = ip.indexOf(indEnd);
			if (pos > -1) {
				ip = ip.substring(0, pos);
			}
			if (!ip.startsWith("127.")) {
				return ip;
			}
		}

		return null;
	}

	static {
		try {
			SERVER_IP = getInnerServerIp();
		} catch (Throwable e) {
			SERVER_IP = "127.0.0.1";
		}
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.utils.Envs JD-Core Version: 0.5.4
 */