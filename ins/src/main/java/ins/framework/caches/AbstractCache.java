package ins.framework.caches;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<T> implements Cache<T> {
	Map<Integer, CacheMap<T>> cacheMap;

	public AbstractCache() {
		this.cacheMap = new HashMap();
	}

	public static <T, U> T[] copyOfRange(U[] original, int from, int to,
			Class<? extends T[]> newType) {
		int newLength = to - from;
		if (newLength < 0)
			throw new IllegalArgumentException(from + " > " + to);
		Object[] copy = (Object[]) (Object[]) Array.newInstance(
				newType.getComponentType(), newLength);

		System.arraycopy(original, from, copy, 0,
				Math.min(original.length - from, newLength));

		return (T[]) copy;
	}

	public static <T> T[] copyOfRange(T[] original, int from, int to) {
		return (T[]) copyOfRange(original, from, to, original.getClass());
	}

	public abstract CacheMap<T> createCacheMap();

	public T get(Object[] args) {
		if (args == null) {
			throw new NullPointerException("the args should be null");
		}
		if (args.length < 1) {
			throw new IllegalArgumentException("the args's number at least 1");
		}
		Map map = getMap(copyOfRange(args, 0, args.length - 1));
		Object key = args[(args.length - 1)];
		return (T) map.get(key);
	}

	private Map<Object, T> getMap(Object[] args) {
		Integer size = Integer.valueOf(args.length);
		Map map = (Map) this.cacheMap.get(size);
		if (map == null) {
			synchronized (this) {
				map = createCacheMap();
				this.cacheMap.put(size, (CacheMap) map);
			}
		}
		for (int i = 0; i < args.length; ++i) {
			Map m = (Map) map.get(args[i]);
			if (m == null) {
				m = createSubMap();
				map.put(args[i], m);
			}
			map = m;
		}
		return map;
	}

	public abstract Map createSubMap();

	public T set(Object[] args) {
		if (args == null) {
			throw new NullPointerException("the args should be null");
		}
		if (args.length < 2) {
			throw new IllegalArgumentException("the args's number at least 2");
		}
		Map map = getMap(copyOfRange(args, 0, args.length - 2));
		Object key = args[(args.length - 2)];
		Object value = args[(args.length - 1)];
		return (T) map.put(key, value);
	}

	public T del(Object[] args) {
		if (args == null) {
			throw new NullPointerException("the args should be null");
		}
		if (args.length < 1) {
			throw new IllegalArgumentException("the args's number at least 1");
		}
		Map map = getMap(copyOfRange(args, 0, args.length - 1));
		Object key = args[(args.length - 1)];
		return (T) map.remove(key);
	}

	public boolean has(Object[] args) {
		if (args == null) {
			throw new NullPointerException("the args should be null");
		}
		if (args.length < 1) {
			throw new IllegalArgumentException("the args's number at least 1");
		}
		Map map = getMap(copyOfRange(args, 0, args.length - 1));
		Object key = args[(args.length - 1)];
		return map.containsKey(key);
	}

	public void clear(Object[] args) {
		if (args == null) {
			throw new NullPointerException("the args should be null");
		}
		Map map = getMap(args);
		map.clear();
	}

	public void clearAll() {
		for (CacheMap cm : this.cacheMap.values())
			cm.clear();
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.caches.AbstractCache JD-Core Version: 0.5.4
 */