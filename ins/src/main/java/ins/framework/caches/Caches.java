package ins.framework.caches;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Caches {
	public static final Cache STATIC = new AbstractCache() {
		public CacheMap createCacheMap() {
			return new CacheMap() {
				Map map;

				public Object set(Object key, Object value) {
					return this.map.put(key, value);
				}

				public Object del(Object key) {
					return this.map.remove(key);
				}

				public boolean has(Object key) {
					return this.map.containsKey(key);
				}

				public void reset() {
					this.map.clear();
				}

				public Object fetch(Object key) {
					return this.map.get(key);
				}
			};
		}

		public Map createSubMap() {
			return new HashMap();
		}
	};

	public static final Cache THREAD = new AbstractCache() {
		public CacheMap createCacheMap() {
			return new CacheMap() {
				ThreadLocal<Map> tl;

				public Map getMap() {
					Map map = (Map) this.tl.get();
					if (map == null) {
						map = new HashMap();
						this.tl.set(map);
					}
					return map;
				}

				public Object set(Object key, Object value) {
					return getMap().put(key, value);
				}

				public Object del(Object key) {
					return getMap().remove(key);
				}

				public boolean has(Object key) {
					return getMap().containsKey(key);
				}

				public void reset() {
					getMap().clear();
				}

				public Object fetch(Object key) {
					return getMap().get(key);
				}
			};
		}

		public Map createSubMap() {
			return new HashMap();
		}
	};

	public static void main(String[] args) {
		THREAD.set(new Object[] { "1", "2", "3", "value" });
		THREAD.set(new Object[] { "1", "2", "value2" });
		THREAD.set(new Object[] { "1", "value3" });
		THREAD.set(new Object[] { "2", "value4" });
		Object s = THREAD.get(new Object[] { "1", "2", "3" });
		System.out.println(s);
		s = THREAD.get(new Object[] { "1", "2" });
		System.out.println(s);
		s = THREAD.get(new Object[] { "1" });
		System.out.println(s);
		s = THREAD.get(new Object[] { "2" });
		System.out.println(s);
		THREAD.set(new Object[] { "2", "value5" });
		s = THREAD.get(new Object[] { "1", "2", "3" });
		System.out.println(s);
		s = THREAD.get(new Object[] { "1", "2" });
		System.out.println(s);
		s = THREAD.get(new Object[] { "1" });
		System.out.println(s);
		s = THREAD.get(new Object[] { "2" });
		System.out.println(s);

		System.out.println("**" + THREAD.has(new Object[] { "1", "2", "3" }));
		System.out.println(THREAD.del(new Object[] { "1", "2", "3" }));
		System.out.println("**" + THREAD.has(new Object[] { "1", "2", "3" }));

		System.out.println("--" + THREAD.get(new Object[] { "1", "2", "3" }));
		THREAD.clear(new Object[] { "1", "2" });
		System.out.println("--" + THREAD.get(new Object[] { "1", "2", "3" }));

		System.out.println("~~" + THREAD.get(new Object[] { "1", "2" }));
		THREAD.clearAll();
		System.out.println("~~" + THREAD.get(new Object[] { "1", "2" }));
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.caches.Caches JD-Core Version: 0.5.4
 */