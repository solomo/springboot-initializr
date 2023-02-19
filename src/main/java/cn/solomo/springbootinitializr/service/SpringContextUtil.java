package cn.solomo.springbootinitializr.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author solom
 * @Description // TODO
 * @version: v1.0.0
 * @Date 2022-2-24 17:10
 **/
@Component
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	
	/**
	 * 获取applicationContext
	 *
	 * @return the application context
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 通过name获取 Bean.
	 *
	 * @param name the name
	 * @return the bean
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
	
	/**
	 * 通过class获取Bean.
	 *
	 * @param <T>   the type parameter
	 * @param clazz the clazz
	 * @return the bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}
	
	/**
	 * 通过name,以及Clazz返回指定的Bean
	 *
	 * @param <T>   the type parameter
	 * @param name  the name
	 * @param clazz the clazz
	 * @return the bean
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtil.applicationContext == null) {
			SpringContextUtil.applicationContext = applicationContext;
		}
	}
	
}