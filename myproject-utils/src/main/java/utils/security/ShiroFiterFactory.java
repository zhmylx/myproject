package utils.security;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;

/**
 * 扩展ShiroFilterFactoryBean 
 * 增加注入ChainDefinition 实现动态的路径拦截
 * 如：数据库读取路径 权限 然后封装 /account/user!save* = perms[user:manage]
 * @author Homing tsang
 *
 */
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
