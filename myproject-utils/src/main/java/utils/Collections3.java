package utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

public class Collections3 {
	/**
	 * a-b
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> List<T> subtract(Collection<T> a, Collection<T> b) {
		ArrayList list = new ArrayList(a);
		for (Iterator localIterator = b.iterator(); localIterator.hasNext();) {
			Object element = localIterator.next();
			list.remove(element);
		}
		return list;
	}
/**
 * key value 对象集合 转换为map对象
 * @param collection
 * @param keyPropertyName
 * @param valuePropertyName
 * @return
 */
	public static Map extractToMap(Collection collection,
			String keyPropertyName, String valuePropertyName) {
		Map map = new HashMap(collection.size());
		try {
			for (Iterator localIterator = collection.iterator(); localIterator
					.hasNext();) {
				Object obj = localIterator.next();
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),
						PropertyUtils.getProperty(obj, valuePropertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return map;
	}

	public static List extractToList(Collection collection, String propertyName) {
		List list = new ArrayList(collection.size());
		try {
			for (Iterator localIterator = collection.iterator(); localIterator
					.hasNext();) {
				Object obj = localIterator.next();
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	public static String extractToString(Collection collection,
			String propertyName, String separator) {
		List list = extractToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	public static String convertToString(Collection collection, String separator) {
		return StringUtils.join(collection, separator);
	}

	public static String convertToString(Collection collection, String prefix,
			String postfix) {
		StringBuilder builder = new StringBuilder();
		for (Iterator localIterator = collection.iterator(); localIterator
				.hasNext();) {
			Object o = localIterator.next();
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}
}
