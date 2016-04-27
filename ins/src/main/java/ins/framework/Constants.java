package ins.framework;

import java.util.Calendar;

public class Constants {
	public static String APP_NAME = "";
	public static String APP_VERSION = "";
	public static String APP_DESC = "";
	public static String APP_TITLE = "";
	public static final String DEFAULT_CONTEXT = "classpath*:spring/applicationContext*.xml";
	public static final String DEFAULT_TEST_CONTEXT = "classpath*:spring/test/applicationContext*.xml";
	public static final String DEFAULT_TEST_DATASOURCE_CONTEXT = "classpath*:spring/test/dataAccessContext*.xml";

	static {
		if ("@app.name@".startsWith("@app.name"))
			APP_NAME = "Arch5-Framework";
		else {
			APP_NAME = "@app.name@";
		}
		if ("@app.version@".startsWith("@app.version"))
			APP_VERSION = "5.0开发版";
		else {
			APP_VERSION = "@app.version@";
		}
		if ("@license.to@".startsWith("@license"))
			APP_TITLE = APP_NAME + " V" + APP_VERSION + " 中科软内部测试";
		else {
			APP_TITLE = APP_NAME + " V" + APP_VERSION + " @license.to@";
		}
		APP_DESC = "<html>" + APP_NAME + "是中科软第5代框架的基础支撑包，对Web开发所需的各种组件进行了整合。"
				+ "<br>作者：中科软科技股份有限公司金融保险二部技术平台组" + "<br>版本：" + APP_VERSION
				+ "<br>版权所有   @中科软科技股份有限公司  " + Calendar.getInstance().get(1)
				+ "</html>";
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.Constants JD-Core Version: 0.5.4
 */