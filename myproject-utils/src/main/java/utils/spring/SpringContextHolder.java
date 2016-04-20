 package utils.spring;
 
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.DisposableBean;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ApplicationContext;
 import org.springframework.context.ApplicationContextAware;
 
 public class SpringContextHolder
   implements ApplicationContextAware, DisposableBean
 {
   private static ApplicationContext applicationContext = null;
 
   private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
 
   @Autowired
   public void setApplicationContext(ApplicationContext applicationContext)
   {
     logger.debug("注入ApplicationContext到SpringContextHolder:" + applicationContext);
 
     if (applicationContext != null) {
       logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + 
         applicationContext);
     }
 
     this.applicationContext = applicationContext;
   }
 
   public void destroy()
     throws Exception
   {
     clear();
   }
 
   public static ApplicationContext getApplicationContext()
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
     logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
     applicationContext = null;
   }
 
   private static void assertContextInjected()
   {
     if (applicationContext == null)
       throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
   }
 }

