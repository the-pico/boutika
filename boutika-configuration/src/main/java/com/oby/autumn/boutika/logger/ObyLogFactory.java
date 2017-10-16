package com.oby.autumn.boutika.logger;



public class ObyLogFactory {

	/**
	 * Constructeur privé
	 */
	private ObyLogFactory() {
	}
	
	public static IObyLogger getInstance() {
		return getInstance(null);
	}
	
	public static synchronized IObyLogger getInstance(Class<?> sender) {
		final IObyLogger raiserLog = (IObyLogger)StaticSpringContextProvider.getApplicationContext().getBean(IObyLogger.class);
		raiserLog.setSender(sender);
		return raiserLog;
	}
	
}
