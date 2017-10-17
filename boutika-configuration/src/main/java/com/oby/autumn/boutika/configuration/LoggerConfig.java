package com.oby.autumn.boutika.configuration;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.oby.autumn.boutika.configuration.logger.IObyLogger;
import com.oby.autumn.boutika.configuration.logger.ObyLog4j;
import com.oby.autumn.boutika.configuration.logger.StaticSpringContextProvider;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.oby.autumn.boutika" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class LoggerConfig {
	
	/**
	 * Nom du fichier properties contenant le paramétrage de log4j
	 */
	private static final String LOG4J_CONFIGURATION_FILE_NAME = "log4j";
	
//	/**
//	 * Preffix dans l'annuaire JNDI des parametres dans config/com surchargeant
//	 * ceux du fichier log4j.properties
//	 */
//	private static final String LOG4J_PROPERTIES_JNDI_PREFIX = "log4j";
	
	/**
	 * L'idée ici est d'utiliser l'interface ApplicationContextAware afin que Spring
	 * partage son context et que l'on puisse le stocker dans une variable static.
	 * Cela permet a des class non Spring d'accéder au context.
	 * 
	 * Pas d'Autolog sur les bean nécessaire à son fonctionnement
	 * 
	 * @return StaticSpringContextProvider
	 */
	@Bean(name="staticSpringContextProvider")
	@Scope(value="singleton")
	@Lazy(value=false)
	public StaticSpringContextProvider getStaticSpringContextProvider() {
		return new StaticSpringContextProvider();
	}
	
	@Bean(name="log4jProperties")
	@Scope(value="singleton")
	public Properties getLog4jProperties() throws RuntimeException {
		try {
			final Properties log4jProperties = new Properties();
			
			//Chargement depuis le fichier log4j.properties
			final ResourceBundle bundle = ResourceBundle.getBundle(LOG4J_CONFIGURATION_FILE_NAME);
			for (String key: bundle.keySet()) {
				log4jProperties.setProperty(key, bundle.getString(key));
	        }
			
//			//Update de la configuration avec config/com
//			getCommonConfig().getSubset(LOG4J_PROPERTIES_JNDI_PREFIX).fillProperties(log4jProperties, LOG4J_PROPERTIES_JNDI_PREFIX);
			
			//Call log4j API with the properties loaded
			PropertyConfigurator.configure(log4jProperties);
			
			return log4jProperties;
		} catch(Exception e) {
			throw new RuntimeException(this.getClass().getName(), e);
		}
	}
	
	@Bean(name="obyLog4j")
	@DependsOn(value={"staticSpringContextProvider", "log4jProperties"})
	public IObyLogger getRaiserLog() {
		
		return new ObyLog4j();
	}

}
