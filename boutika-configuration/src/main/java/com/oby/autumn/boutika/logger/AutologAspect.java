package com.oby.autumn.boutika.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AutologAspect {

	/**
	 * Message de log d'entrée dans une méthode @Autolog
	 * methodName - IN  With PARAMS: [args...]
	 */
	private static final String BEFORE = "%s - IN  With PARAMS: %s";

	/**
	 * Message de log de sortie en cas d'exception dans une méthode @Autolog
	 * methodName - OUT With EXCEPTION: errorMessage
	 */
	private static final String ERROR = "%s - OUT With EXCEPTION: %s";

	/**
	 * Message de log de sortie normale dans une méthode @Autolog
	 * methodName - OUT With RETURN: returnObject
	 */
	private static final String RETURN = "%s - OUT With RETURN: %s";
	
	/**
	 * Message de log permettant de tracer les performances dans une méthode @Autolog
	 * methodName - EXECUTE in 20 ms
	 */
	private static final String PERFORM = "%s - EXECUTE in %s ms";

	/**
	 * Point de jointure avec les bean annotés par autolog
	 * On prend donc en compte les classes annotées @Autolog
	 */
	@Pointcut("within(@com.oby.autumn.boutika.logger.Autolog *)")
	public void beanAnnotatedAutolog() {
	}

	/**
	 * Point de jointure avec les methodes public
	 * Parmis ces classes, on va surtout s'interressé aux méthodes publique
	 */
	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
	}
	
	/**
	 * Point de jointure avec les methodes protected
	 * Parmis ces classes, on va surtout s'interressé aux méthodes protected
	 */
	@Pointcut("execution(protected * *(..))")
	public void protectedMethod() {
	}
	
	/**
	 * Point de jointure pour les constructeur
	 * On souhaite exclure les constructeurs de l'autolog
	 */
	@Pointcut("execution(*.new(..))")
	public void constructorMethod() {
		
	}

	/**
	 * Point de jointure avec les methodes annotées @Autolog
	 * On prend aussi en compte les méthodes annotées @Autolog
	 */
	@Pointcut("@annotation(com.oby.autumn.boutika.logger.Autolog)")
	public void methodAnnotedAutoLog() {
	}

	/**
	 * point de jointure avec les methodes public dont la classe est @Autolog
	 */
	@Pointcut("(publicMethod() || protectedMethod()) && beanAnnotatedAutolog()")
	public void publicAndProtectedMethodInsideAutoLogBean() {
	}

	/**
	 * Cette methode permet de tracer automatiquement les I/O et les exceptions
	 * de toutes les méthodes qui repondent au critère des PointCut :
	 * !@annotation(NoAutolog) && ( methodAnnotedAutoLog() || publicAndProtectedMethodInsideAutoLogBean() ) 
	 * 
	 * Il n'est pas nécessaire de tester le niveau de log car ce test est inclus dans l'API IRaiserLog
	 * 
	 * @param joinPoint ProceedingJoinPoint
	 * @return L'objet initialement retourné par le joinPoint
	 * @throws Throwable
	 */
	@Around("!constructorMethod() && !@annotation(com.oby.autumn.boutika.logger.NoAutolog) && ( methodAnnotedAutoLog() || publicAndProtectedMethodInsideAutoLogBean() )")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//Objet de gestion des log avec le sender de la log précisé dans le constructeur
		final IObyLogger obyLog = ObyLogFactory.getInstance(joinPoint.getSignature().getDeclaringType());
		
		//Appel au log en entree de fonction avec le niveau info
		//Le niveau info est requis ici car on souhaite obtenir les log applicatives
		//a un niveau suppérieur que l'ensemble des API du marché afin d'être moins
		//pollué lors de l'étude de l'erreur.
		obyLog.info(BEFORE, joinPoint.getSignature().getName(), joinPoint.getArgs());
	
		//Recupération de timer avant l'execution de la méthode demandée
		final long start = System.currentTimeMillis();
		
		try {
			Object returnObject = joinPoint.proceed();
			
			//Appel au log de sortie en cas de return simple avec un niveau info
			//Meme raison que l'input
			obyLog.info(RETURN, joinPoint.getSignature().getName(), returnObject);
			
			return returnObject;
		} catch (Throwable throwable) {
			//Appel au log de sortie en cas d'erreur de la fonction avec le niveau error
			//Ce niveau error est celui habituellement défini en production et l'on
			//souhaite que les exceptions y soient visible.
			obyLog.error(throwable, ERROR, joinPoint.getSignature().getName(), throwable.getMessage());
			throw throwable;
		} finally {
			//Appel au log afin d'afficher le temps d'execution de la méthode demandée
			obyLog.info(PERFORM, joinPoint.getSignature().getName(), System.currentTimeMillis() - start);
		}
	}
	
}
