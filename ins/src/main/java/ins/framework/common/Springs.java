package ins.framework.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class Springs implements ApplicationListener {

	@Autowired
	private ApplicationContext applicationContext;
	private static ApplicationContext context;

	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if (applicationEvent instanceof ContextRefreshedEvent)
			context = this.applicationContext;
	}

	public static <T> T getBean(Class<? extends T> beanClass, String beanName) {
		Assert.notNull(context,
				"The spring applicationContext was not initialized");

		return (T) context.getBean(beanName);
	}

	public static Object getBean(String beanName) {
		Assert.notNull(context,
				"The spring applicationContext was not initialized");

		return context.getBean(beanName);
	}

	public static <T> T getBean(Class<? extends T> beanClass) {
		Assert.notNull(context,
				"The spring applicationContext was not initialized");

		return context.getBean(beanClass);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.common.Springs JD-Core Version: 0.5.4
 */