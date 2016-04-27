package com.cpersicum.modules.utils.mapper;

import com.cpersicum.modules.utils.ReflectionUtils;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;

public class ConvertUtils {
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	static {
		registerDateConverter();
	}

	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	public static <T> List<T> mapList(Collection sourceList,
			Class<T> destinationClass) {
		List destinationList = Lists.newArrayList();
		for (Iterator localIterator = sourceList.iterator(); localIterator
				.hasNext();) {
			Object sourceObject = localIterator.next();
			Object destinationObject = dozer
					.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value,
					toType);
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
	}

	private static void registerDateConverter() {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		org.apache.commons.beanutils.ConvertUtils.register(dc, Date.class);
	}

	public static List extractElementPropertyToList(Collection collection,
			String propertyName) {
		Set set = new HashSet();
		try {
			for (Iterator localIterator = collection.iterator(); localIterator
					.hasNext();) {
				Object obj = localIterator.next();
				set.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		List list = new ArrayList();
		list.addAll(set);
		return list;
	}

	public static String extractElementPropertyToString(Collection collection,
			String propertyName, String separator) {
		List list = extractElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}
}

