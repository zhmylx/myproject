package ins.framework.jndi;

import javax.naming.NamingException;
import org.springframework.jndi.JndiObjectFactoryBean;

public class JndiObjectFindFactoryBean extends JndiObjectFactoryBean {
	protected Object lookupWithFallback() throws NamingException {
		Object jndiObject = null;
		try {
			jndiObject = super.lookupWithFallback();
		} catch (NamingException e) {
			super.setResourceRef(!super.isResourceRef());
			jndiObject = super.lookupWithFallback();
		}
		return jndiObject;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.jndi.JndiObjectFindFactoryBean JD-Core Version:
 * 0.5.4
 */