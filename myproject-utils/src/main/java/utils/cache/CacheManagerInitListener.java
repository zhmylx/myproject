 package utils.cache;
 
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

