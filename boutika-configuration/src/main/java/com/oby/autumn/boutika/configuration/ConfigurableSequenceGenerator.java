package com.oby.autumn.boutika.configuration;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;


public class ConfigurableSequenceGenerator implements PersistentIdentifierGenerator, Configurable {

	private static String default_impl = "org.hibernate.id.enhanced.SequenceStyleGenerator";

	private final IdentifierGenerator impl;

	public ConfigurableSequenceGenerator() {
		String impl_name = System.getProperty("hibernate_sequence_generator");
		if (impl_name == null) {
			impl_name = default_impl;
		}
		try {
			Class<?> clazz = Class.forName(impl_name);
			Object o = clazz.newInstance();
			if (o instanceof IdentifierGenerator) {
				this.impl = (IdentifierGenerator) o;
			} else {
				throw new IllegalArgumentException("class " + impl_name + " is does not implement "
						+ IdentifierGenerator.class.getName());
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("class " + impl_name + " was not found in current classpath", e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("class " + impl_name + " could not be instanciated", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("class " + impl_name + " could not be instanciated", e);
		}
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return impl.generate(session, object);
	}

	@Override
	public void registerExportables(Database database) {
		if (impl instanceof PersistentIdentifierGenerator) {
			((PersistentIdentifierGenerator) impl).registerExportables(database);
		}
		
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		if (impl instanceof Configurable) {
			((Configurable) impl).configure(type, params, serviceRegistry);
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
		if (impl instanceof PersistentIdentifierGenerator) {
			return ((PersistentIdentifierGenerator) impl).sqlCreateStrings(dialect);
		}
		return new String[0];
	}

	@SuppressWarnings("deprecation")
	@Override
	public String[] sqlDropStrings(Dialect dialect) throws HibernateException {
		if (impl instanceof PersistentIdentifierGenerator) {
			return ((PersistentIdentifierGenerator) impl).sqlDropStrings(dialect);
		}
		return new String[0];
	}

	@Override
	public Object generatorKey() {
		if (impl instanceof PersistentIdentifierGenerator) {
			return ((PersistentIdentifierGenerator) impl).generatorKey();
		}
		return "DEFAULT";
	}

}
