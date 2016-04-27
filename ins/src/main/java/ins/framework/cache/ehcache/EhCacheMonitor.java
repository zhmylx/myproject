package ins.framework.cache.ehcache;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

public class EhCacheMonitor {
	public EhCacheMonitor() {
		CacheManager manager = CacheManager.create();
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		boolean registerCacheManager = true;
		boolean registerCaches = true;
		boolean registerCacheConfigurations = true;
		boolean registerCacheStatistics = true;
		ManagementService.registerMBeans(manager, mBeanServer,
				registerCacheManager, registerCaches,
				registerCacheConfigurations, registerCacheStatistics);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.cache.ehcache.EhCacheMonitor JD-Core Version:
 * 0.5.4
 */