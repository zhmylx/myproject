package com.cpersicum.modules.mobiles.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestConfig {
	List<Config> configs = new ArrayList();

	@XmlElementWrapper(name = "configs")
	@XmlElement(name = "config")
	public List<Config> getConfigs() {
		return this.configs;
	}

	public void setConfigs(List<Config> configs) {
		this.configs = configs;
	}

	public void addConfing(String id, String beanName, String serverName,
			String methodName) {
		Config cf = new Config(id, beanName, serverName, methodName);
		this.configs.add(cf);
	}
}

