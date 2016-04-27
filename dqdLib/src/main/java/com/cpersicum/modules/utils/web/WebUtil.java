package com.cpersicum.modules.utils.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtil {
	public static <T> T requestParam2Bean(HttpServletRequest request, T bean) {
		try {
			Map map = request.getParameterMap();

			ConvertUtils.register(new Converter() {
				public Object convert(Class type, Object value) {
					if (value == null) {
						return null;
					}
					String str = ((String) value).trim();
					if (str.trim().equals("")) {
						return null;
					}
					Date d = null;
					SimpleDateFormat format = null;

					if (str.length() > 10) {
						if (str.indexOf("-") > 0)
							format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						else {
							format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						}
					} else if (str.indexOf("-") > 0)
						format = new SimpleDateFormat("yyyy-MM-dd");
					else {
						format = new SimpleDateFormat("yyyy/MM/dd");
					}
					try {
						d = format.parse(str);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
					return d;
				}
			}, Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

