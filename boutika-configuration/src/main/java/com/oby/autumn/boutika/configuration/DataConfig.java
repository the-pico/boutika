package com.oby.autumn.boutika.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "com.oby.autumn.boutika" })
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.oby.autumn.boutika" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class DataConfig {

	@Bean
	public DataSource dataSource() throws RuntimeException {
		try {
			return (DataSource) new JndiTemplate().lookup("java:/comp/env/jdbc/centralDb");
		} catch (Exception e) {
			throw new RuntimeException(this.getClass().getName(), e);
		}
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		try {

			final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setJpaProperties(additionalProperties());
			// factory.setJpaVendorAdapter(vendorAdapter);
			factory.setPackagesToScan("com.oby.autumn.boutika");
			factory.setDataSource(dataSource());
			factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);

			factory.afterPropertiesSet();

			return factory;
		} catch (Exception e) {
			throw new RuntimeException(this.getClass().getName(), e);
		}

	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private Properties additionalProperties() {
		final Properties properties = new Properties();

		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.cache.use_query_cache", "false");
		properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");

		// properties.setProperty("hibernate.max_fetch_depth", "0");
		// properties.setProperty("current_session_context_class", "thread");

		return properties;
	}

}
