package ins.framework.cache;

import ins.framework.cache.info.CacheManagerInfo;

public class CacheManagerMonitor implements CacheManagerMonitorMBean {
	public void clearCacheManager(String cacheManagerName) {
		CacheManager.getInstance(cacheManagerName).clearCacheManager(
				cacheManagerName);
	}

	public String[] getAllCacheManagerName(String cacheName) {
		return CacheManager.getInstance(cacheName).getAllCacheManagerName();
	}

	public CacheManagerInfo getCacheManagerInfo(String cacheName) {
		return CacheManager.getInstance(cacheName).getCacheManagerInfo(
				cacheName);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.CacheManagerMonitor JD-Core Version:
 * 0.5.4
 */