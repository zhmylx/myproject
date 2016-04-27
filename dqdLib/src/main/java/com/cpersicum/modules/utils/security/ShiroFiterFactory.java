package com.cpersicum.modules.utils.security;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;

public class ShiroFiterFactory extends ShiroFilterFactoryBean {
	private ChainDefinition chainDefinition;

	public ChainDefinition getChainDefinition() {
		return this.chainDefinition;
	}

	public void setChainDefinition(ChainDefinition chainDefinition) {
		this.chainDefinition = chainDefinition;
	}

	public void setFilterChainDefinitions(String definitions) {
		StringBuilder bs = new StringBuilder(definitions);

		if (this.chainDefinition != null) {
			bs.append(this.chainDefinition.getFilterChainDefinitions());
		}
		definitions = bs.toString();
		Ini ini = new Ini();
		ini.load(definitions);

		Ini.Section section = ini.getSection("urls");
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection("");
		}
		setFilterChainDefinitionMap(section);
	}
}

