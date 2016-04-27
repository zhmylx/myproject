package utils.cache;

import utils.cache.info.CacheManagerInfo;


public abstract interface CacheManagerMonitorMBean
{
  public abstract String[] getAllCacheManagerName(String paramString);

  public abstract void clearCacheManager(String paramString);

  public abstract CacheManagerInfo getCacheManagerInfo(String paramString);
}

