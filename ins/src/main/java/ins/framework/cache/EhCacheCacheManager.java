package ins.framework.cache;

import ins.framework.cache.info.CacheManagerInfo;
import ins.framework.utils.Datas;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Statistics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class EhCacheCacheManager implements CacheService {
	private static final Log logger = LogFactory
			.getLog(EhCacheCacheManager.class);
	private static Map<String, Object> allCacheMap = Collections
			.synchronizedMap(new HashMap(0));
	private String cacheName;
	private Cache cache;
	private static CacheManager cacheManager = CacheManager.getInstance();

	public EhCacheCacheManager(String cacheName) {
		this.cacheName = cacheName;
		if (!cacheManager.cacheExists(cacheName)) {
			cacheManager.addCache(cacheName);
		}
		this.cache = cacheManager.getCache(cacheName);
	}

	public static EhCacheCacheManager getInstance(String cacheManagerName) {
		if (allCacheMap.containsKey(cacheManagerName)) {
			return (EhCacheCacheManager) allCacheMap.get(cacheManagerName);
		}
		EhCacheCacheManager cacheManagerUtil = new EhCacheCacheManager(
				cacheManagerName);
		allCacheMap.put(cacheManagerName, cacheManagerUtil);
		return cacheManagerUtil;
	}

	public String[] getAllCacheManagerName() {
		return getAllCacheManagerNameStatic();
	}

	public static String[] getAllCacheManagerNameStatic() {
		return cacheManager.getCacheNames();
	}

	public static void clearAllCacheManagerStatic() {
		String[] cacheNameArray = cacheManager.getCacheNames();
		for (int i = 0; i < cacheNameArray.length; ++i)
			cacheManager.getCache(cacheNameArray[i]).removeAll();
	}

	public void clearAllCacheManager() {
		clearAllCacheManagerStatic();
	}

	public Object getCache(String key) {
		Element element = this.cache.get(key);
		Object value = null;
		if (element != null)
			value = element.getObjectValue();
		else {
			return null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug(Datas.join(new Object[] { "Retrieve data key=", key,
					",value=", value }));
		}
		return value;
	}

	public void putCache(String key, Object value) {
		Element element = new Element(key, value);
		this.cache.put(element);
		if (logger.isDebugEnabled())
			logger.debug(Datas.join(new Object[] { "Put cache key=", key,
					",value=", value }));
	}

	public void clearCache(Object[] arguments) {
		if ((arguments == null) || (arguments.length == 0)) {
			int size = this.cache.getSize();
			this.cache.removeAll();
			if (logger.isWarnEnabled()) {
				logger.warn(Datas.join(new Object[] { "Clear ", this.cacheName,
						" total ", Integer.valueOf(size), " item(s)." }));
			}
			return;
		}
		String keyPrefix = generateKey(arguments);
		String keyName = "";
		Object[] keys = this.cache.getKeys().toArray();
		int size = 0;
		for (int i = 0; i < keys.length; ++i) {
			keyName = (String) keys[i];
			if (keyName == null) {
				continue;
			}
			if (keyName.startsWith(keyPrefix)) {
				this.cache.remove(keyName);
				++size;
			}
		}
		if (logger.isWarnEnabled())
			logger.warn(Datas.join(new Object[] { "Clear ", this.cacheName,
					" ", Integer.valueOf(size), " item(s).prefix=", keyPrefix }));
	}

	public boolean containsKey(String key) {
		return this.cache.isKeyInCache(key);
	}

	public boolean containsValue(String value) {
		return this.cache.isValueInCache(value);
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
		if (!cacheManager.cacheExists(cacheManagerName)) {
			return null;
		}
		Cache cache = cacheManager.getCache(cacheManagerName);
		CacheManagerInfo info = new CacheManagerInfo();
		info.setCacheName(cacheManagerName);
		info.setCacheType(2);
		info.setCacheImplClass(EhCacheCacheManager.class.getName());
		Statistics cacheStatistics = cache.getStatistics();
		info.setSizeInByte(cache.calculateInMemorySize());
		info.setSize(cache.getSize());
		info.setMemoryStoreSize(cache.getMemoryStoreSize());
		info.setDiskStoreSize(cache.getDiskStoreSize());
		info.setStatisticsAccuracy(cacheStatistics.getStatisticsAccuracy());
		info.setCacheHits(cacheStatistics.getCacheHits());
		info.setOnDiskHits(cacheStatistics.getOnDiskHits());
		info.setInMemoryHits(cacheStatistics.getInMemoryHits());
		info.setCacheMisses(cacheStatistics.getCacheMisses());
		info.setAverageGetTime(cacheStatistics.getAverageGetTime());
		info.setEvictionCount(cacheStatistics.getEvictionCount());
		return info;
	}

	public void clearCacheManager(String cacheManagerName) {
		if (!cacheManager.cacheExists(cacheManagerName)) {
			return;
		}
		Cache cache = cacheManager.getCache(cacheManagerName);
		cache.removeAll();
	}

	public int size() {
		if (this.cache == null) {
			return 0;
		}
		return this.cache.getSize();
	}

	public Object[] getKeys() {
		return this.cache.getKeys().toArray();
	}

	public void remove(String key) {
		this.cache.remove(key);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.EhCacheCacheManager JD-Core Version:
 * 0.5.4
 */