package com.oby.autumn.boutika.logger;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class StaticSpringContextProvider implements ApplicationContextAware {
	
	/**
	 * Context de Spring
	 */
	private static ApplicationContext applicationContext;

	/**
	 * Implémentation lié à l'interface ApplicationContextAware que Spring appel apres la
	 * création de l'objet. Elle nous permet de stocker le context de Spring dans une
	 * variable static et de la rendre accessible de n'importe ou.
	 * @param context ApplicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		StaticSpringContextProvider.applicationContext = applicationContext;
	}
	
	/**
	 * Cette fonction permet de récupérer le context de Spring depuis des class non
	 * gérées par celui ci
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
  
}
