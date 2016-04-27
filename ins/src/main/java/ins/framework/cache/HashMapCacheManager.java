package ins.framework.cache;

import ins.framework.cache.info.CacheManagerInfo;
import ins.framework.utils.Datas;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class HashMapCacheManager implements CacheService {
	private static final Log logger = LogFactory
			.getLog(HashMapCacheManager.class);
	private static Map<String, HashMapCacheManager> allCacheMap = Collections
			.synchronizedMap(new HashMap(0));

	private Map<String, Object> cacheMap = Collections
			.synchronizedMap(new HashMap(0));
	private String cacheName;

	private HashMapCacheManager(String cacheName) {
		this.cacheName = cacheName;
	}

	public static HashMapCacheManager getInstance(String cacheManagerName) {
		HashMapCacheManager cacheManager = (HashMapCacheManager) allCacheMap
				.get(cacheManagerName);
		if (cacheManager != null) {
			return cacheManager;
		}
		cacheManager = new HashMapCacheManager(cacheManagerName);
		allCacheMap.put(cacheManagerName, cacheManager);
		return cacheManager;
	}

	public String[] getAllCacheManagerName() {
		return getAllCacheManagerNameStatic();
	}

	public static String[] getAllCacheManagerNameStatic() {
		String[] nameArray = new String[allCacheMap.size()];
		allCacheMap.keySet().toArray(nameArray);
		return nameArray;
	}

	public static void clearAllCacheManagerStatic() {
		String[] cacheNameArray = new String[allCacheMap.size()];
		allCacheMap.keySet().toArray(cacheNameArray);
		for (int i = 0; i < cacheNameArray.length; ++i)
			getInstance(cacheNameArray[i]).clearCache(new Object[0]);
	}

	public void clearAllCacheManager() {
		clearAllCacheManagerStatic();
	}

	public Object getCache(String key) {
		Object value = this.cacheMap.get(key);
		if (logger.isDebugEnabled()) {
			logger.debug(Datas.join(new Object[] { "Retrieve data key=", key,
					",value=", value }));
		}
		return value;
	}

	public void putCache(String key, Object value) {
		this.cacheMap.put(key, value);
		if (logger.isDebugEnabled())
			logger.debug(Datas.join(new Object[] { "Put cache key=", key,
					",value=", value }));
	}

	private Map<String, Object> getCacheMap() {
		return this.cacheMap;
	}

	public void clearCache(Object[] arguments) {
		if ((arguments == null) || (arguments.length == 0)) {
			int size = this.cacheMap.size();
			this.cacheMap.clear();
			if (logger.isWarnEnabled()) {
				logger.warn(Datas.join(new Object[] { "Clear ", this.cacheName,
						" total ", Integer.valueOf(size), " item(s)." }));
			}
			return;
		}
		String keyPrefix = generateKey(arguments);
		String keyName = "";
		Object[] keys = this.cacheMap.keySet().toArray();
		int size = 0;
		for (int i = 0; i < keys.length; ++i) {
			keyName = (String) keys[i];
			if (keyName == null) {
				continue;
			}
			if (keyName.startsWith(keyPrefix)) {
				this.cacheMap.remove(keyName);
				++size;
			}
		}
		if (logger.isWarnEnabled())
			logger.warn(Datas.join(new Object[] { "Clear ", this.cacheName,
					" ", Integer.valueOf(size), " item(s).prefix=", keyPrefix }));
	}

	public boolean containsKey(String key) {
		return this.cacheMap.containsKey(key);
	}

	public boolean containsValue(String value) {
		return this.cacheMap.containsValue(value);
	}

	public String generateCacheKey(Object[] arguments) {
		Assert.isTrue(arguments != null);
		Assert.isTrue(arguments.length > 0);
		return generateKey(arguments);
	}

	private String generateKey(Object[] arguments) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arguments.length - 1; ++i) {
			sb.append(arguments[i]).append(".");
		}
		sb.append(arguments[(arguments.length - 1)]);
		return sb.toString();
	}

	public CacheManagerInfo getCacheManagerInfo(String cacheManagerName) {
		return getCacheManagerInfoStatic(cacheManagerName);
	}

	public static CacheManagerInfo getCacheManagerInfoStatic(
			String cacheManagerName) {
		if (!allCacheMap.containsKey(cacheManagerName)) {
			return null;
		}
		HashMapCacheManager cache = getInstance(cacheManagerName);
		CacheManagerInfo info = new CacheManagerInfo();
		info.setCacheName(cacheManagerName);
		info.setCacheType(1);
		info.setCacheImplClass(HashMapCacheManager.class.getName());
		info.setSize(cache.getCacheMap().size());
		info.setMemoryStoreSize(cache.getCacheMap().size());
		info.setDiskStoreSize(0L);
		info.setStatisticsAccuracy(-1);
		info.setCacheHits(-1L);
		info.setOnDiskHits(-1L);
		info.setInMemoryHits(-1L);
		info.setCacheMisses(-1L);
		info.setAverageGetTime(-1.0F);
		info.setEvictionCount(-1L);
		return info;
	}

	public void clearCacheManager(String cacheManagerName) {
		if (allCacheMap.containsKey(cacheManagerName))
			getInstance(cacheManagerName).clearCache(new Object[0]);
	}

	public int size() {
		if (this.cacheMap == null) {
			return 0;
		}
		return this.cacheMap.size();
	}

	public Object[] getKeys() {
		return this.cacheMap.keySet().toArray();
	}

	public void remove(String key) {
		this.cacheMap.remove(key);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.HashMapCacheManager JD-Core Version:
 * 0.5.4
 */