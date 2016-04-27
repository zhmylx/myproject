package ins.framework.caches;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class CacheMap<T> extends AbstractMap<Object, T> {
	public abstract T set(Object paramObject1, Object paramObject2);

	public abstract T del(Object paramObject);

	public abstract boolean has(Object paramObject);

	public abstract void reset();

	public abstract T fetch(Object paramObject);

	public boolean containsKey(Object key) {
		return has(key);
	}

	public T get(Object key) {
		return fetch(key);
	}

	public T put(Object key, T value) {
		return set(key, value);
	}

	public T remove(Object key) {
		return del(key);
	}

	public void clear() {
		reset();
	}

	public Set<Map.Entry<Object, T>> entrySet() {
		throw new UnsupportedOperationException();
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.caches.CacheMap JD-Core Version: 0.5.4
 */