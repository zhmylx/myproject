 package ins.framework.cache;
 
 import javax.servlet.ServletContext;
 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;
 
 public class CacheManagerInitListener
   implements ServletContextListener
 {
   public void contextDestroyed(ServletContextEvent sce)
   {
   }
 
   public void contextInitialized(ServletContextEvent sce)
   {
     String cacheType = sce.getServletContext().getInitParameter("cacheType");
     if ((cacheType != null) && (cacheType.equalsIgnoreCase("ehcache")))
       CacheManager.setCacheType(2);
   }
 }

/* Location:           C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:     ins.framework.cache.CacheManagerInitListener
 * JD-Core Version:    0.5.4
 */