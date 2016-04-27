 package com.cpersicum.modules.test.spring;
 
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.context.ApplicationContext;
 import org.springframework.mock.web.MockServletContext;
 import org.springframework.web.context.ConfigurableWebApplicationContext;
 import org.springframework.web.context.ContextLoader;
 import org.springframework.web.context.WebApplicationContext;
 import org.springframework.web.context.support.XmlWebApplicationContext;
 
 public class SpringWebTestHelper
 {
   public static void initWebApplicationContext(MockServletContext servletContext, String[] configLocations)
   {
     String configLocationsString = StringUtils.join(configLocations, ",");
     servletContext.addInitParameter("contextConfigLocation", configLocationsString);
     new ContextLoader().initWebApplicationContext(servletContext);
   }
 
   public static void initWebApplicationContext(MockServletContext servletContext, ApplicationContext applicationContext)
   {
     ConfigurableWebApplicationContext wac = new XmlWebApplicationContext();
     wac.setParent(applicationContext);
     wac.setServletContext(servletContext);
     wac.setConfigLocation("");
     servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);
     wac.refresh();
   }
 
   public static void closeWebApplicationContext(MockServletContext servletContext)
   {
     new ContextLoader().closeWebApplicationContext(servletContext);
   }
 }

/* Location:           E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar
 * Qualified Name:     com.cpersicum.modules.test.spring.SpringWebTestHelper
 * JD-Core Version:    0.5.3
 */