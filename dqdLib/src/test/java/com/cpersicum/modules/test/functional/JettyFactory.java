package com.cpersicum.modules.test.functional;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyFactory {
	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";

	public static Server createServerInSource(int port, String contextPath) {
		Server server = new Server();

		server.setStopAtShutdown(true);

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);

		connector.setReuseAddress(false);
		server.setConnectors(new Connector[] { connector });

		WebAppContext webContext = new WebAppContext("src/main/webapp",
				contextPath);

		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		server.setHandler(webContext);

		return server;
	}
}

