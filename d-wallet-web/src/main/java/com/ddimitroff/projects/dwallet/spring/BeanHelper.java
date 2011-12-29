package com.ddimitroff.projects.dwallet.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanHelper {

	private static final String DATABASE_CONFIG_FILE_NAME = "/db/database-config.xml";
	private static final String SPRING_SERVLET_CONFIG_FILE_NAME = "/spring/spring-servlet.xml";
	private static final String DWALLET_CONTEXT_CONFIG_FILE_NAME = "/spring/dwallet-context.xml";

	private static final Logger logger = Logger.getLogger(BeanHelper.class);

	private static volatile ApplicationContext applicationContext;

	/**
	 * Returns the bean instance with the given class and name.
	 * 
	 * @param <T>
	 *          bean type
	 * @param beanClass
	 * @param beanName
	 * @return bean
	 */
	public synchronized static <T> T getBean(Class<T> beanClass, String beanName) {
		return beanClass.cast(getApplicationContext().getBean(beanName));
	}

	public synchronized static <T> T getBean(Class<T> beanClass, String beanName, Object[] args) {
		return beanClass.cast(getApplicationContext().getBean(beanName, args));
	}

	public synchronized static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(new String[] { DATABASE_CONFIG_FILE_NAME, SPRING_SERVLET_CONFIG_FILE_NAME,
					DWALLET_CONTEXT_CONFIG_FILE_NAME });
			logger.info("Spring application context initialized successfully");
		}

		return applicationContext;
	}

	public synchronized static void closeApplicationContext() {
		logger.info("Closing Spring application context");
		try {
			((AbstractApplicationContext) applicationContext).close();
		} catch (Throwable t) {
			logger.warn("Failed closing Spring application context", t);
		}
	}
}
