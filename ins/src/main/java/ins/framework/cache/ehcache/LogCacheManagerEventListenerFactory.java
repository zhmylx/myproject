package ins.framework.cache.ehcache;

import ins.framework.utils.Datas;
import java.util.Properties;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogCacheManagerEventListenerFactory extends
		CacheManagerEventListenerFactory {
	private static final Log logger = LogFactory
			.getLog(LogCacheManagerEventListenerFactory.class);

	public CacheManagerEventListener createCacheManagerEventListener(
			Properties properties) {
		return new CacheManagerEventListener() {
			public void dispose() throws CacheException {
			}

			public Status getStatus() {
				return null;
			}

			public void init() throws CacheException {
			}

			public void notifyCacheAdded(String cacheName) {
				if (LogCacheManagerEventListenerFactory.logger.isInfoEnabled())
					LogCacheManagerEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Cache [", cacheName,
									"] added" }));
			}

			public void notifyCacheRemoved(String cacheName) {
				if (LogCacheManagerEventListenerFactory.logger.isInfoEnabled())
					LogCacheManagerEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Cache [", cacheName,
									"] removed" }));
			}
		};
	}

	public CacheManagerEventListener createCacheManagerEventListener(
			CacheManager arg0, Properties arg1) {
		throw new IllegalStateException("unavailable!");
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:
 * ins.framework.cache.ehcache.LogCacheManagerEventListenerFactory JD-Core
 * Version: 0.5.4
 */