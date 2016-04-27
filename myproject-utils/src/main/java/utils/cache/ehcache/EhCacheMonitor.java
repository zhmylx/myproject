package utils.cache.ehcache;

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
		ManagementService.registerMBeans(manager, mBeanServer,registerCacheManager, registerCaches,registerCacheConfigurations, registerCacheStatistics);
	}
}
