package com.cpersicum.modules.mobiles.config;

import com.cpersicum.modules.utils.Exceptions;
import com.cpersicum.modules.utils.mapper.JaxbMapper;
import com.cpersicum.modules.utils.mapper.JaxbMapper.CollectionWrapper;
import java.util.HashMap;
import java.util.Map;

public class PlaceholderConfigurer {
	public static Map<String, Config> requestConfig = new HashMap();

	public static Config getConfig(String requestCode) {
		Config config = null;
		config = (Config) requestConfig.get(requestCode);
		if (config == null) {
			config = initConfig(requestCode);
		}
		return config;
	}

	private static Config initConfig(String requestCode) {
		Config result = null;
		JaxbMapper binder = new JaxbMapper(new Class[] { RequestConfig.class,
				JaxbMapper.CollectionWrapper.class });
		RequestConfig bean = (RequestConfig) binder
				.fromXmlFile("requestConfig.xml");
		for (Config config : bean.getConfigs()) {
			setXmlBaenClass(config);
			requestConfig.put(config.getId(), config);
			if (requestCode.equals(config.getId())) {
				result = config;
			}
		}
		return result;
	}

	private static void setXmlBaenClass(Config config) {
		try {
			String beanName = config.getBeanName();
			config.setXmlBeanClass(Class.forName(beanName));
		} catch (ClassNotFoundException e) {
			throw Exceptions.unchecked(e);
		}
	}
}

