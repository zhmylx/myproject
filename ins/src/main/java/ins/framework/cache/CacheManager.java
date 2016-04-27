package ins.framework.cache;

import java.io.PrintStream;

public class CacheManager {
	private static int cacheType = 1;

	public static int getCacheType() {
		return cacheType;
	}

	public static void setCacheType(int cacheType) {
		cacheType = cacheType;
		switch (cacheType) {
		case 2:
			System.out
					.println("CacheManager.setCacheType(CacheService.EHCACHE_CACHE)");

			break;
		case 1:
			System.out
					.println("CacheManager.setCacheType(CacheService.HASHMAP_CACHE)");

			break;
		default:
			System.out
					.println("Unsupport cacheType,use defalut CacheService.HASHMAP_CACHE");

			cacheType = 1;
		}
	}

	public static CacheService getInstance(String cacheManagerName) {
		CacheService cacheService;
		if (cacheType == 2)
			cacheService = EhCacheCacheManager.getInstance(cacheManagerName);
		else {
			cacheService = HashMapCacheManager.getInstance(cacheManagerName);
		}
		return cacheService;
	}

	public static void clearAllCacheManager() {
		if (cacheType == 2)
			EhCacheCacheManager.clearAllCacheManagerStatic();
		else
			HashMapCacheManager.clearAllCacheManagerStatic();
	}

	public static String[] getAllCacheManagerName() {
		if (cacheType == 2) {
			return EhCacheCacheManager.getAllCacheManagerNameStatic();
		}
		return HashMapCacheManager.getAllCacheManagerNameStatic();
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.CacheManager JD-Core Version: 0.5.4
 */