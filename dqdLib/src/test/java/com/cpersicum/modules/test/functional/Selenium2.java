package com.cpersicum.modules.test.functional;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2 {
	public static final int DEFAULT_WAIT_TIME = 20;
	private WebDriver driver;
	private String baseUrl;

	public Selenium2(WebDriver driver, String baseUrl) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		setTimeout(20);
	}

	public Selenium2(WebDriver driver) {
		this(driver, "");
	}

	public void setStopAtShutdown() {
		Runtime.getRuntime().addShutdownHook(new Thread("Selenium Quit Hook") {
			public void run() {
				Selenium2.this.quit();
			}
		});
	}

	public void open(String url) {
		String urlToOpen = (url.indexOf("://") == -1) ? this.baseUrl
				+ ((!(url.startsWith("/"))) ? "/" : "") + url : url;
		this.driver.get(urlToOpen);
	}

	public String getLocation() {
		return this.driver.getCurrentUrl();
	}

	public void back() {
		this.driver.navigate().back();
	}

	public void refresh() {
		this.driver.navigate().refresh();
	}

	public String getTitle() {
		return this.driver.getTitle();
	}

	public void quit() {
		try {
			this.driver.quit();
		} catch (Exception e) {
			System.err.println("Error happen while quit selenium :"
					+ e.getMessage());
		}
	}

	public void setTimeout(int seconds) {
		this.driver.manage().timeouts()
				.implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public WebElement findElement(By by) {
		return this.driver.findElement(by);
	}

	public List<WebElement> findElements(By by) {
		return this.driver.findElements(by);
	}

	public boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
		}
		return false;
	}

	public boolean isVisible(By by) {
		return this.driver.findElement(by).isDisplayed();
	}

	public void type(By by, String text) {
		WebElement element = this.driver.findElement(by);
		element.clear();
		element.sendKeys(new CharSequence[] { text });
	}

	public void click(By by) {
		this.driver.findElement(by).click();
	}

	public void check(By by) {
		WebElement element = this.driver.findElement(by);
		check(element);
	}

	public void check(WebElement element) {
		if (!(element.isSelected()))
			element.click();
	}

	public void uncheck(By by) {
		WebElement element = this.driver.findElement(by);
		uncheck(element);
	}

	public void uncheck(WebElement element) {
		if (element.isSelected())
			element.click();
	}

	public boolean isChecked(By by) {
		WebElement element = this.driver.findElement(by);
		return isChecked(element);
	}

	public boolean isChecked(WebElement element) {
		return element.isSelected();
	}

	public Select getSelect(By by) {
		return new Select(this.driver.findElement(by));
	}

	public String getText(By by) {
		return this.driver.findElement(by).getText();
	}

	public String getValue(By by) {
		return getValue(this.driver.findElement(by));
	}

	public String getValue(WebElement element) {
		return element.getAttribute("value");
	}

	public void waitForVisible(By by, int timeout) {
		waitForCondition(ExpectedConditions.visibilityOfElementLocated(by),
				timeout);
	}

	public void waitForTextPresent(By by, String text, int timeout) {
		waitForCondition(ExpectedConditions.textToBePresentInElement(by, text),
				timeout);
	}

	public void waitForValuePresent(By by, String value, int timeout) {
		waitForCondition(
				ExpectedConditions.textToBePresentInElementValue(by, value),
				timeout);
	}

	public void waitForCondition(ExpectedCondition conditon, int timeout) {
		new WebDriverWait(this.driver, timeout).until(conditon);
	}

	public boolean isTextPresent(String text) {
		String bodyText = this.driver.findElement(By.tagName("body")).getText();
		return bodyText.contains(text);
	}

	public String getTable(WebElement table, int rowIndex, int columnIndex) {
		return table.findElement(
				By.xpath("//tr[" + (rowIndex + 1) + "]//td["
						+ (columnIndex + 1) + "]")).getText();
	}

	public String getTable(By by, int rowIndex, int columnIndex) {
		return getTable(this.driver.findElement(by), rowIndex, columnIndex);
	}
}
