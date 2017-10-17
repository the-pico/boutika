package com.oby.autumn.boutika.configuration.logger;


import org.slf4j.Logger;

public interface IObyLogger {

	/**
	 * Cette fonction permet d'obtenir le logger root
	 * @return Logger
	 */
	public Logger getLogger();
	
	/**
	 * Cette fonction permet d'obtenir le logger root ou le logger en fonction
	 * de la class fourni en parametre.
	 * 
	 * @param sender Class<?> - Class à l'origine de l'appel de log ou null
	 * @return Logger
	 */
	public Logger getLogger(Class<?> sender);
	
	/**
	 * Cette fonction permet d'obtenir le niveau de log du Threshold
	 * @return RaiserLogLevel
	 */
	public ObyLogLevel getThresholdLevel();
	
	/**
	 * Cette fonction permet d'obtenir le niveau de log du Threshold
	 * @param sender Class<?> - Class à l'origine de l'appel de log ou null
	 * @return RaiserLogLevel
	 */
	public ObyLogLevel getThresholdLevel(Class<?> sender);
	
	/**
	 * SETTER
	 * @param level RaiserLogLevel - 
	 */
	public void setThresholdLevel(ObyLogLevel level);
	
	/**
	 * SETTER
	 * @param level RaiserLogLevel - 
	 * @param sender Class<?> - Class à l'origine de l'appel de log ou null
	 */
	public void setThresholdLevel(ObyLogLevel level, Class<?> sender);

	/**
	 * SETTER
	 * @param level RaiserLogLevel - 
	 */
	public void setLevel(ObyLogLevel level);
	
	/**
	 * SETTER
	 * @param level RaiserLogLevel - 
	 * @param sender Class<?> - Class à l'origine de l'appel de log ou null
	 */
	public void setLevel(ObyLogLevel level, Class<?> sender);
	
	/**
	 * GETTER
	 * @return RaiserLogLevel
	 */
	public ObyLogLevel getLevel();
	
	/**
	 * GETTER
	 * @param sender Class<?> - Class à l'origine de l'appel de log ou null
	 * @return RaiserLogLevel
	 */
	public ObyLogLevel getLevel(Class<?> sender);
	
	/**
	 * GETTER
	 * Retourne le type de class à l'origine
	 * de cette instance d'objet de log
	 * @return Class<?>
	 */
	public Class<?> getSender();
	
	/**
	 * SETTER
	 * Permet de définir le type de class à l'origine de cette instance
	 * d'objet de log par defaut et permet de ne pas le fournir a chaque
	 * appel de fonction
	 * 
	 * @param sender Class<?>
	 */
	public void setSender(Class<?> sender);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void trace(String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void trace(Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void trace(Throwable throwable, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void trace(Throwable throwable, Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void debug(String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void debug(Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void debug(Throwable throwable, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void debug(Throwable throwable, Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void info(String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void info(Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void info(Throwable throwable, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void info(Throwable throwable, Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void warn(String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void warn(Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void warn(Throwable throwable, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void warn(Throwable throwable, Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void error(String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void error(Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void error(Throwable throwable, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void error(Throwable throwable, Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void fatal(String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param message String - Message à afficher dans la log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void fatal(Class<?> sender, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void fatal(Throwable throwable, String message, Object... args);
	
	/**
	 * Cette fonction a pour objectif de logger selon le paramétrage en cours
	 * via l'API SLF4J
	 * 
	 * Cette fonction peut être appelé sans aucun argument pour valoriser args
	 * 
	 * String formater :
	 * http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
	 * 
	 * @param throwable Throwable - Exception à l'origine de cet appel de log
	 * @param sender Class<?> - Class à l'origine de l'appel de log
	 * @param message String - Message à afficher dans la log
	 * @param args Object... - Tableau d'Objet utilisé pour la fonction String.format
	 */
	public void fatal(Throwable throwable, Class<?> sender, String message, Object... args);
	
	/**
	 * Cette methode permet de tester si niveau de log activer est DEBUG
	 * @return boolean
	 */
	public boolean isDebugEnabled();
	
	/**
	 * Cette methode permet de tester si niveau de log activer est TRACE
	 * @return boolean
	 */
	public boolean isTraceEnabled();
	
	/**
	 * Cette methode permet de tester si niveau de log activer est WARN
	 * @return boolean
	 */
	public boolean isWarnEnabled();
	
	/**
	 * Cette methode permet de tester si niveau de log activer est ERROR
	 * @return boolean
	 */
	public boolean isErrorEnabled();
	
	/**
	 * Cette methode permet de tester si niveau de log activer est INFO
	 * @return boolean
	 */
	public boolean isInfoEnabled();
	
}
