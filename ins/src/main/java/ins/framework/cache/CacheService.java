package ins.framework.cache;

import ins.framework.cache.info.CacheManagerInfo;

public abstract interface CacheService
{
  public static final int HASHMAP_CACHE = 1;
  public static final int EHCACHE_CACHE = 2;

  public abstract String[] getAllCacheManagerName();

  public abstract void clearAllCacheManager();

  public abstract void clearCacheManager(String paramString);

  public abstract Object getCache(String paramString);

  public abstract void putCache(String paramString, Object paramObject);

  public abstract void clearCache(Object[] paramArrayOfObject);

  public abstract boolean containsKey(String paramString);

  public abstract boolean containsValue(String paramString);

  public abstract String generateCacheKey(Object[] paramArrayOfObject);

  public abstract int size();

  public abstract Object[] getKeys();

  public abstract void remove(String paramString);

  public abstract CacheManagerInfo getCacheManagerInfo(String paramString);
}

/* Location:           C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:     ins.framework.cache.CacheService
 * JD-Core Version:    0.5.4
 */