package com.cpersicum.modules.test.functional;

import com.cpersicum.modules.utils.Exceptions;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.util.Assert;

public class WebDriverFactory {
	public static WebDriver createDriver(String driverName) {
		WebDriver driver = null;

		if (BrowserType.firefox.name().equals(driverName)) {
			driver = new FirefoxDriver();
		} else if (BrowserType.ie.name().equals(driverName)) {
			driver = new InternetExplorerDriver();
		} else if (BrowserType.chrome.name().equals(driverName)) {
			driver = new ChromeDriver();
		} else if (BrowserType.htmlunit.name().equals(driverName)) {
			driver = new HtmlUnitDriver(true);
		} else if (driverName.startsWith(BrowserType.remote.name())) {
			String[] params = driverName.split(":");
			Assert.isTrue(
					params.length == 4,
					"Remote driver is not right, accept format is \"remote:localhost:4444:firefox\", but the input is\""
							+ driverName + "\"");

			String remoteHost = params[1];
			String remotePort = params[2];
			String driverType = params[3];
			DesiredCapabilities cap = null;

			if (BrowserType.firefox.name().equals(driverType))
				cap = DesiredCapabilities.firefox();
			else if (BrowserType.ie.name().equals(driverType))
				cap = DesiredCapabilities.internetExplorer();
			else if (BrowserType.chrome.name().equals(driverType)) {
				cap = DesiredCapabilities.chrome();
			}
			try {
				driver = new RemoteWebDriver(new URL("http://" + remoteHost
						+ ":" + remotePort + "/wd/hub"), cap);
			} catch (MalformedURLException e) {
				Exceptions.unchecked(e);
			}
		}

		Assert.notNull(driver, "Driver could be found by name:" + driverName);

		return driver;
	}

	public static enum BrowserType {
		firefox, ie, chrome, htmlunit, remote;
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.test.functional.WebDriverFactory JD-Core Version: 0.5.3
 */