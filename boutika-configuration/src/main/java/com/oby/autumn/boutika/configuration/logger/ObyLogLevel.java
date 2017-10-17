package com.oby.autumn.boutika.configuration.logger;

import org.apache.log4j.Level;

public enum ObyLogLevel {

	ALL(Level.ALL), 
	TRACE(Level.TRACE), 
	DEBUG(Level.DEBUG), 
	INFO(Level.INFO), 
	WARN(Level.WARN), 
	ERROR(Level.ERROR), 
	FATAL(Level.FATAL), 
	OFF(Level.OFF);
	
	/**
	 * Niveau log4j associé
	 */
	private Level level;
	
	/**
	 * Constructeur
	 * @param level Level
	 */
	private ObyLogLevel(Level level) {
		this.level = level;
	}
	
	/**
	 * GETTER
	 * @return Level
	 */
	public Level getLevel() {
		return this.level;
	}
	
	public static ObyLogLevel getLevel(Level level) {
		
		if(level==null){return null;}
		
		switch (level.toInt()) {
			case Level.OFF_INT:
				return OFF;
			case Level.FATAL_INT:
				return FATAL;
			case Level.ERROR_INT:
				return ERROR;
			case Level.WARN_INT:
				return WARN;
			case Level.INFO_INT:
				return INFO;
			case Level.DEBUG_INT:
				return DEBUG;
			case Level.ALL_INT:
				return ALL;
			case Level.TRACE_INT:
				return TRACE;
			default:
				return null;
		}
	}
	
	public static Level getLevel(ObyLogLevel level) {
		
		if(level==null){return null;}
		
		switch (level) {
			case OFF:
				return Level.OFF;
			case FATAL:
				return Level.FATAL;
			case ERROR:
				return Level.ERROR;
			case WARN:
				return Level.WARN;
			case INFO:
				return Level.INFO;
			case DEBUG:
				return Level.DEBUG;
			case ALL:
				return Level.ALL;
			case TRACE:
				return Level.TRACE;
			default:
				return null;
		}
	}
	
}
