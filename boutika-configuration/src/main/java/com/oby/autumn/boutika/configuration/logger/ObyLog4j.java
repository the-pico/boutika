package com.oby.autumn.boutika.configuration.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

public class ObyLog4j implements IObyLogger {

	private Class<?> sender;

	public ObyLog4j(Class<?> sender) {
		this.sender = sender;
	}

	public ObyLog4j() {
	}

	public ObyLogLevel getThresholdLevel() {
		return getThresholdLevel(this.getSender());
	}

	public ObyLogLevel getThresholdLevel(Class<?> sender) {
		return ObyLogLevel.getLevel(getLog4jLogger(sender).getLoggerRepository().getThreshold());
	}

	public void setThresholdLevel(ObyLogLevel level) {
		setThresholdLevel(level, this.getSender());
	}

	public void setThresholdLevel(ObyLogLevel level, Class<?> sender) {
		getLog4jLogger(sender).getLoggerRepository().setThreshold(level.getLevel());
	}

	public void setLevel(ObyLogLevel level) {
		setLevel(level, this.getSender());
	}

	public void setLevel(ObyLogLevel level, Class<?> sender) {
		getLog4jLogger(sender).setLevel(level.getLevel());
	}

	public ObyLogLevel getLevel() {
		return getLevel(this.getSender());
	}

	public ObyLogLevel getLevel(Class<?> sender) {
		if (getLog4jLogger(sender).getLevel() != null) {
			// Il y a un niveau de log associé à la class elle meme
			// ou au root logger si le sender est null
			return ObyLogLevel.getLevel(getLog4jLogger(sender).getLevel());
		} else {
			// On cherche a retourner le niveau de log associé au package parent
			// car il ne semble pas associé a la classe elle meme
			final Category logCat = getLog4jLogger(sender).getParent();
			if (logCat == null) {
				// rootCategory, pas de parent possible
				// cas normalement impossible
				return ObyLogLevel.getLevel(getLog4jLogger(sender).getLevel());
			} else {
				// Utilisation du parent pour obtenir un level parlant
				// Cela permet de rendre l'info du niveau de log appliqué sur le package
				return ObyLogLevel.getLevel(logCat.getLevel());
			}
		}
	}

	public Class<?> getSender() {
		return sender;
	}

	public void setSender(Class<?> sender) {
		this.sender = sender;
	}

	public Logger getLogger() {
		return getLogger(this.getSender());
	}

	public Logger getLogger(Class<?> sender) {
		if (sender == null) {
			return LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		} else {
			return LoggerFactory.getLogger(sender);
		}
	}

	private org.apache.log4j.Logger getLog4jLogger(Class<?> sender) {
		if (sender == null) {
			return org.apache.log4j.Logger.getRootLogger();
		} else {
			return org.apache.log4j.Logger.getLogger(sender);
		}
	}

	public void trace(String message, Object... args) {
		this.trace(null, getSender(), message, args);
	}

	public void trace(Class<?> sender, String message, Object... args) {
		this.trace(null, sender, message, args);
	}

	public void trace(Throwable throwable, String message, Object... args) {
		this.trace(throwable, getSender(), message, args);
	}

	public void trace(Throwable throwable, Class<?> sender, String message, Object... args) {
		if (getLogger(sender).isTraceEnabled()) {
			if (throwable == null) {
				getLogger(sender).trace(buildMessage(sender, message, args));
			} else {
				getLogger(sender).trace(buildMessage(sender, message, args), throwable);
			}
		}
	}

	public void debug(String message, Object... args) {
		this.debug(null, getSender(), message, args);
	}

	public void debug(Class<?> sender, String message, Object... args) {
		this.debug(null, sender, message, args);
	}

	public void debug(Throwable throwable, String message, Object... args) {
		this.debug(throwable, getSender(), message, args);
	}

	public void debug(Throwable throwable, Class<?> sender, String message, Object... args) {
		if (getLogger(sender).isDebugEnabled()) {
			if (throwable == null) {
				getLogger(sender).debug(buildMessage(sender, message, args));
			} else {
				getLogger(sender).debug(buildMessage(sender, message, args), throwable);
			}
		}
	}

	public void info(String message, Object... args) {
		this.info(null, getSender(), message, args);
	}

	public void info(Class<?> sender, String message, Object... args) {
		this.info(null, sender, message, args);
	}

	public void info(Throwable throwable, String message, Object... args) {
		this.info(throwable, getSender(), message, args);
	}

	public void info(Throwable throwable, Class<?> sender, String message, Object... args) {
		if (getLogger(sender).isInfoEnabled()) {
			if (throwable == null) {
				getLogger(sender).info(buildMessage(sender, message, args));
			} else {
				getLogger(sender).info(buildMessage(sender, message, args), throwable);
			}
		}
	}

	public void warn(String message, Object... args) {
		this.warn(null, getSender(), message, args);
	}

	public void warn(Class<?> sender, String message, Object... args) {
		this.warn(null, sender, message, args);
	}

	public void warn(Throwable throwable, String message, Object... args) {
		this.warn(throwable, getSender(), message, args);
	}

	public void warn(Throwable throwable, Class<?> sender, String message, Object... args) {
		if (getLogger(sender).isWarnEnabled()) {
			if (throwable == null) {
				getLogger(sender).warn(buildMessage(sender, message, args));
			} else {
				getLogger(sender).warn(buildMessage(sender, message, args), throwable);
			}
		}
	}

	public void error(String message, Object... args) {
		this.error(null, getSender(), message, args);
	}

	public void error(Class<?> sender, String message, Object... args) {
		this.error(null, sender, message, args);
	}

	public void error(Throwable throwable, String message, Object... args) {
		this.error(throwable, getSender(), message, args);
	}

	public void error(Throwable throwable, Class<?> sender, String message, Object... args) {
		if (getLogger(sender).isErrorEnabled()) {
			if (throwable == null) {
				getLogger(sender).error(buildMessage(sender, message, args));
			} else {
				getLogger(sender).error(buildMessage(sender, message, args), throwable);
			}
		}
	}

	public void fatal(String message, Object... args) {
		this.fatal(null, getSender(), message, args);
	}

	public void fatal(Class<?> sender, String message, Object... args) {
		this.fatal(null, sender, message, args);
	}

	public void fatal(Throwable throwable, String message, Object... args) {
		this.fatal(throwable, getSender(), message, args);
	}

	public void fatal(Throwable throwable, Class<?> sender, String message, Object... args) {
		if (getLogger(sender).isErrorEnabled(MarkerFactory.getMarker("FATAL"))) {
			if (throwable == null) {
				getLogger(sender).error(MarkerFactory.getMarker("FATAL"), buildMessage(sender, message, args));
			} else {
				getLogger(sender).error(MarkerFactory.getMarker("FATAL"), buildMessage(sender, message, args),
						throwable);
			}
		}
	}

	private String buildMessage(Class<?> sender, String message, Object... args) {
		final StringBuilder sb = new StringBuilder();

		// Si on connait la class d'origine du message, on le précise
		if (sender != null) {
			sb.append("[").append(sender.getSimpleName()).append("]").append(" ");
		}

		if (args != null && args.length > 0) {
			// Il y a des arguments dans le tableau

			// On va parcourir chaque élément du tableau pour repérer les tableaux
			// d'Object afin
			// d'améliorer la qualité du message affiché dans la log:
			// [Ljava.lang.Object;@21662166 => [123, valParam]

			final String limit = "5000";

			// Verifier le parametre !!
			if (limit != null && Integer.valueOf(limit) > 0) {
				// On format le message en fonction des arguments
				sb.append("[TRUNCAT(").append(limit).append(")] ").append(
						String.format(message, getWellFormedArgs(getLimitedArgs(args, Integer.valueOf(limit)))));
			} else {
				// On format le message en fonction des arguments
				sb.append(String.format(message, getWellFormedArgs(args)));
			}

		} else {
			// On prend le message tel quel
			sb.append(message);
		}

		return sb.toString();
	}

	private String getStringValue(Object[] args) {
		final StringBuilder builder = new StringBuilder("[");

		// pour eviter le if d'impression du séparateur dans la boucle
		if (args.length > 0) {
			builder.append(String.valueOf(args[0]));
		}

		for (int i = 1; i < args.length; i++) {
			builder.append(" , ");

			builder.append(String.valueOf(args[i]));
		}

		builder.append("]");

		return builder.toString();
	}

	private Object[] getWellFormedArgs(Object[] tabObj) {
		for (int i = 0; i < tabObj.length; i++) {
			if (tabObj[i] != null && tabObj[i].getClass().isArray()) {
				// On cherche a améliorer l'affichage des tableau dans la log
				tabObj[i] = getStringValue((Object[]) tabObj[i]);
			}
		}
		return tabObj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] getLimitedArgs(Object[] tabObj, int limit) {

		// Fabrique d'un tableau temporaire pour la log
		Object[] tabRet = new Object[tabObj.length];

		// si un des args est une Coll, Map ou Array, alors il faut regarder le
		// parametre
		// et limiter la taille en fonction afin d'optimiser la conso de ressources et
		// eviter les outofmemories sur des listes trop grande.
		for (int i = 0; i < tabObj.length; i++) {
			if (tabObj[i] == null) {
				tabRet[i] = null;
			} else if (tabObj[i] instanceof Collection) {
				// Fabrication d'une nouvelle Collection
				int n = 0;
				final List list = new ArrayList();

				final Iterator iter = ((Collection) tabObj[i]).iterator();
				while (n < limit && iter.hasNext()) {
					list.add(iter.next());
					n++;
				}

				tabRet[i] = list;
			} else if (tabObj[i] instanceof Map) {
				// Fabrication d'une nouvelle Map
				int n = 0;
				final Map hmap = new HashMap();

				final Iterator iter = ((Map) tabObj[i]).entrySet().iterator();
				while (n < limit && iter.hasNext()) {
					final Entry thisEntry = (Entry) iter.next();
					hmap.put(thisEntry.getKey(), thisEntry.getValue());
					n++;
				}

				tabRet[i] = hmap;
			} else if (tabObj[i].getClass().isArray()) {
				// Fabrication d'un nouveau tableau
				tabRet[i] = Arrays.copyOfRange(((Object[]) tabObj[i]), 0,
						Math.min(limit, ((Object[]) tabObj[i]).length));
			} else {
				// Autre type d'élément/objet
				tabRet[i] = tabObj[i];
			}
		}

		return tabRet;
	}

	public boolean isDebugEnabled() {
		return getLogger().isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return getLogger().isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		return getLogger().isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return getLogger().isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return getLogger().isWarnEnabled();
	}

}
