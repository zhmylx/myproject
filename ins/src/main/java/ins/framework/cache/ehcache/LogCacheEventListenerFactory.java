package ins.framework.cache.ehcache;

import ins.framework.utils.Datas;
import java.util.Properties;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogCacheEventListenerFactory extends CacheEventListenerFactory {
	private static final Log logger = LogFactory
			.getLog(LogCacheEventListenerFactory.class);

	public CacheEventListener createCacheEventListener(Properties properties) {
		return new CacheEventListener() {
			public void dispose() {
			}

			public void notifyElementEvicted(Ehcache cache, Element element) {
				if (LogCacheEventListenerFactory.logger.isInfoEnabled())
					LogCacheEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Element ", element.getKey(),
									"=", element.getValue(), " evicted." }));
			}

			public void notifyElementExpired(Ehcache cache, Element element) {
				if (LogCacheEventListenerFactory.logger.isInfoEnabled())
					LogCacheEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Element ", element.getKey(),
									"=", element.getValue(), " expired." }));
			}

			public void notifyElementPut(Ehcache cache, Element element)
					throws CacheException {
				if (LogCacheEventListenerFactory.logger.isInfoEnabled())
					LogCacheEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Element ", element.getKey(),
									"=", element.getValue(), " added." }));
			}

			public void notifyElementRemoved(Ehcache cache, Element element)
					throws CacheException {
				if (LogCacheEventListenerFactory.logger.isInfoEnabled())
					LogCacheEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Element ", element.getKey(),
									"=", element.getValue(), " removed." }));
			}

			public void notifyElementUpdated(Ehcache cache, Element element)
					throws CacheException {
				if (LogCacheEventListenerFactory.logger.isInfoEnabled())
					LogCacheEventListenerFactory.logger.info(Datas
							.join(new Object[] { "Element ", element.getKey(),
									"=", element.getValue(), " updated." }));
			}

			public void notifyRemoveAll(Ehcache cache) {
				if (LogCacheEventListenerFactory.logger.isWarnEnabled())
					LogCacheEventListenerFactory.logger.warn(Datas
							.join(new Object[] { cache.getName(),
									" remove all." }));
			}

			public Object clone() throws CloneNotSupportedException {
				return super.clone();
			}
		};
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.ehcache.LogCacheEventListenerFactory
 * JD-Core Version: 0.5.4
 */