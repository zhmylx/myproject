package ins.framework.cache;

import ins.framework.cache.info.CacheManagerInfo;

public abstract interface CacheManagerMonitorMBean
{
  public abstract String[] getAllCacheManagerName(String paramString);

  public abstract void clearCacheManager(String paramString);

  public abstract CacheManagerInfo getCacheManagerInfo(String paramString);
}

/* Location:           C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:     ins.framework.cache.CacheManagerMonitorMBean
 * JD-Core Version:    0.5.4
 */