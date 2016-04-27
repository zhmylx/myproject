 package utils.cache;

import utils.cache.info.CacheManagerInfo;

 public class CacheManagerMonitor
   implements CacheManagerMonitorMBean
 {
   public void clearCacheManager(String cacheManagerName)
   {
     CacheManager.getInstance(cacheManagerName).clearCacheManager(cacheManagerName);
   }
 
   public String[] getAllCacheManagerName(String cacheName) {
     return CacheManager.getInstance(cacheName).getAllCacheManagerName();
   }
 
   public CacheManagerInfo getCacheManagerInfo(String cacheName) {
     return CacheManager.getInstance(cacheName).getCacheManagerInfo(cacheName);
   }
 }
