package com.cpersicum.modules.utils.security;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyShiroFilterFactoryBean implements FactoryBean, BeanPostProcessor {
	private static final transient Logger log = LoggerFactory
			.getLogger(MyShiroFilterFactoryBean.class);
	private SecurityManager securityManager;
	private Map<String, Filter> filters;
	private Map<String, String> filterChainDefinitionMap;
	private String loginUrl;
	private String successUrl;
	private String unauthorizedUrl;
	private ChainDefinition chainDefinition;
	private AbstractShiroFilter instance;

	public ChainDefinition getChainDefinition() {
		return this.chainDefinition;
	}

	public void setChainDefinition(ChainDefinition chainDefinition) {
		this.chainDefinition = chainDefinition;
	}

	public MyShiroFilterFactoryBean() {
		this.filters = new LinkedHashMap();
		this.filterChainDefinitionMap = new LinkedHashMap();
	}

	public SecurityManager getSecurityManager() {
		return this.securityManager;
	}

	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public String getLoginUrl() {
		return this.loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return this.successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getUnauthorizedUrl() {
		return this.unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public Map<String, Filter> getFilters() {
		return this.filters;
	}

	public void setFilters(Map<String, Filter> filters) {
		this.filters = filters;
	}

	public Map<String, String> getFilterChainDefinitionMap() {
		return this.filterChainDefinitionMap;
	}

	public void setFilterChainDefinitionMap(
			Map<String, String> filterChainDefinitionMap) {
		this.filterChainDefinitionMap = filterChainDefinitionMap;
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

	public Object getObject() throws Exception {
		if (this.instance == null) {
			this.instance = createInstance();
		}
		return this.instance;
	}

	public Class getObjectType() {
		return SpringShiroFilter.class;
	}

	public boolean isSingleton() {
		return true;
	}

	protected FilterChainManager createFilterChainManager() {
		DefaultFilterChainManager manager = new DefaultFilterChainManager();
		Map<String, Filter> defaultFilters = manager.getFilters();

		for (Filter filter : defaultFilters.values()) {
			applyGlobalPropertiesIfNecessary(filter);
		}

		Map<String, Filter> filters = getFilters();
		String name;
		if (!(CollectionUtils.isEmpty(filters))) {
			for (Map.Entry entry : filters.entrySet()) {
				name = (String) entry.getKey();
				Filter filter = (Filter) entry.getValue();
				applyGlobalPropertiesIfNecessary(filter);
				if (filter instanceof Nameable) {
					((Nameable) filter).setName(name);
				}

				manager.addFilter(name, filter, false);
			}

		}

		Map<String, String> chains = getFilterChainDefinitionMap();
		if (!(CollectionUtils.isEmpty(chains))) {
			for (Map.Entry entry : chains.entrySet()) {
				String url = (String) entry.getKey();
				String chainDefinition = (String) entry.getValue();
				manager.createChain(url, chainDefinition);
			}
		}

		return manager;
	}

	protected AbstractShiroFilter createInstance() throws Exception {
		log.debug("Creating Shiro Filter instance.");

		SecurityManager securityManager = getSecurityManager();
		String msg;
		if (securityManager == null) {
			msg = "SecurityManager property must be set.";
			throw new BeanInitializationException(msg);
		}

		if (!(securityManager instanceof WebSecurityManager)) {
			msg = "The security manager does not implement the WebSecurityManager interface.";
			throw new BeanInitializationException(msg);
		}

		FilterChainManager manager = createFilterChainManager();

		PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
		chainResolver.setFilterChainManager(manager);

		return new SpringShiroFilter((WebSecurityManager) securityManager,
				chainResolver);
	}

	private void applyLoginUrlIfNecessary(Filter filter) {
		String loginUrl = getLoginUrl();
		if ((StringUtils.hasText(loginUrl))
				&& (filter instanceof AccessControlFilter)) {
			AccessControlFilter acFilter = (AccessControlFilter) filter;

			String existingLoginUrl = acFilter.getLoginUrl();
			if ("/login.jsp".equals(existingLoginUrl))
				acFilter.setLoginUrl(loginUrl);
		}
	}

	private void applySuccessUrlIfNecessary(Filter filter) {
		String successUrl = getSuccessUrl();
		if ((StringUtils.hasText(successUrl))
				&& (filter instanceof AuthenticationFilter)) {
			AuthenticationFilter authcFilter = (AuthenticationFilter) filter;

			String existingSuccessUrl = authcFilter.getSuccessUrl();
			if ("/".equals(existingSuccessUrl))
				authcFilter.setSuccessUrl(successUrl);
		}
	}

	private void applyUnauthorizedUrlIfNecessary(Filter filter) {
		String unauthorizedUrl = getUnauthorizedUrl();
		if ((StringUtils.hasText(unauthorizedUrl))
				&& (filter instanceof AuthorizationFilter)) {
			AuthorizationFilter authzFilter = (AuthorizationFilter) filter;

			String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
			if (existingUnauthorizedUrl == null)
				authzFilter.setUnauthorizedUrl(unauthorizedUrl);
		}
	}

	private void applyGlobalPropertiesIfNecessary(Filter filter) {
		applyLoginUrlIfNecessary(filter);
		applySuccessUrlIfNecessary(filter);
		applyUnauthorizedUrlIfNecessary(filter);
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof Filter) {
			log.debug("Found filter chain candidate filter '{}'", beanName);
			Filter filter = (Filter) bean;
			applyGlobalPropertiesIfNecessary(filter);
			getFilters().put(beanName, filter);
		} else {
			log.trace("Ignoring non-Filter bean '{}'", beanName);
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	private static final class SpringShiroFilter extends AbstractShiroFilter {
		protected SpringShiroFilter(WebSecurityManager webSecurityManager,
				FilterChainResolver resolver) {
			if (webSecurityManager == null) {
				throw new IllegalArgumentException(
						"WebSecurityManager property cannot be null.");
			}
			setSecurityManager(webSecurityManager);
			if (resolver != null)
				setFilterChainResolver(resolver);
		}
	}
}

