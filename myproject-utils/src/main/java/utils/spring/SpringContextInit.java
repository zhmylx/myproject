 package utils.spring;
 
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.context.support.ClassPathXmlApplicationContext;
 
 public class SpringContextInit
 {
   private static ClassPathXmlApplicationContext applicationContext = null;
 
   private static Logger logger = LoggerFactory.getLogger(SpringContextInit.class);
 
   public void destroy()
     throws Exception
   {
     clear();
   }
 
   public void stop() {
     if (applicationContext == null)
       applicationContext.stop();
   }
 
   public static ClassPathXmlApplicationContext getApplicationContext()
   {
     assertContextInjected();
     return applicationContext;
   }
 
   public static <T> T getBean(String name)
   {
     assertContextInjected();
     return (T) applicationContext.getBean(name);
   }
 
   public static <T> T getBean(Class<T> requiredType)
   {
     assertContextInjected();
     return (T) applicationContext.getBeansOfType(requiredType);
   }
 
   public static void clear()
   {
     logger.debug("清除SpringContextInit中的ApplicationContext:" + applicationContext);
     applicationContext = null;
   }
 
   private static void assertContextInjected()
   {
     if (applicationContext == null) {
       applicationContext = new ClassPathXmlApplicationContext(new String[] { "classpath*:/META-INF/spring/*.xml" });
     applicationContext.start();
    }
   }
 }

