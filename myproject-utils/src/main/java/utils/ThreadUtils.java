 package utils;
 
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.ThreadFactory;
 import java.util.concurrent.TimeUnit;
 import java.util.concurrent.atomic.AtomicInteger;
 
 public abstract class ThreadUtils
 {
   public static void sleep(long millis)
   {
     try
     {
       Thread.sleep(millis);
     }
     catch (InterruptedException localInterruptedException)
     {
     }
   }
 
   public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout, TimeUnit timeUnit)
   {
     pool.shutdown();
     try
     {
       if (!pool.awaitTermination(shutdownTimeout, timeUnit)) {
         pool.shutdownNow();
 
         if (!pool.awaitTermination(shutdownNowTimeout, timeUnit))
           System.err.println("Pool did not terminate");
       }
     }
     catch (InterruptedException ie)
     {
       pool.shutdownNow();
 
       Thread.currentThread().interrupt();
     }
   }
 
   public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit)
   {
     try
     {
       pool.shutdownNow();
       if (!pool.awaitTermination(timeout, timeUnit))
         System.err.println("Pool did not terminate");
     }
     catch (InterruptedException ie) {
       Thread.currentThread().interrupt();
     }
   }
 
   public static class CustomizableThreadFactory
     implements ThreadFactory
   {
     private final String namePrefix;
     private final AtomicInteger threadNumber = new AtomicInteger(1);
 
     public CustomizableThreadFactory(String poolName) {
       this.namePrefix = (poolName + "-");
     }
 
     public Thread newThread(Runnable runable) {
       return new Thread(runable, this.namePrefix + this.threadNumber.getAndIncrement());
     }
   }
 }

