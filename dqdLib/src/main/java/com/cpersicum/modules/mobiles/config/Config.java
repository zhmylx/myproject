package com.cpersicum.modules.mobiles.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Config {
	private String id;
	private String beanName;
	private String serverName;
	private String methodName;
	private Class xmlBeanClass;

	public Config() {
	}

	public Config(String id, String beanName, String serverName,
			String methodName) {
		this.id = id;
		this.beanName = beanName;
		this.serverName = serverName;
		this.methodName = methodName;
	}

	@XmlAttribute
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name = "beanName")
	public String getBeanName() {
		return this.beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	@XmlElement(name = "serverName")
	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@XmlElement(name = "methodName")
	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@XmlTransient
	public Class getXmlBeanClass() {
		return this.xmlBeanClass;
	}

	public void setXmlBeanClass(Class xmlBean) {
		this.xmlBeanClass = xmlBean;
	}
}
