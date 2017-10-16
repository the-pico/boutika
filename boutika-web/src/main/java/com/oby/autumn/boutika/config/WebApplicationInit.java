package com.oby.autumn.boutika.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApplicationInit implements WebApplicationInitializer {

	public static final String DISPATCHER_NAME = "restDispatcher";

	public static final String ENDLOGS_NAME = "endLogs";

	public static final String OUTPUTJSONCONTROLLERFILTER_NAME = "OutputJSONControllerFilter";

	public static final String OUTPUTHTMLCONTROLLERFILTER_NAME = "OutputHTMLControllerFilter";

	public static final String HIDDENHTTPMETHODFILTER_NAME = "HiddenHttpMethodFilter";

	public static final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";

	public static final String URL_CTRL_API_PATTERN = "/ctrl/*";

	public static final String URL_REST_API_PATTERN = "/rest/*";

	public static final String URL_SPRING_WS_PATTERN = "/soap/*";

	public static final String URL_SECURITY_PATTERN = "/*";

	public static final String SPRING_ACTIVE_PROFILE_NAME = "spring.profiles.active";

	public static final String SPRING_HTTP_NAME = "httpFacade";

	public static final String SPRING_SOAP_NAME = "soapDispatcher";

	public static final String URL_SPRING_HTTP_PATTERN = "/remoting/httpFacade";

	public void onStartup(ServletContext container) throws ServletException {
		
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		rootContext.register(WebConfig.class);
		container.addListener(new ContextLoaderListener(rootContext));


		// Servlet Spring MVC
		final ServletRegistration.Dynamic dispatcher = container.addServlet(DISPATCHER_NAME,
				new DispatcherServlet(rootContext));

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(URL_REST_API_PATTERN);
		dispatcher.addMapping(URL_CTRL_API_PATTERN);

		// if (isSecured()) {
		// final FilterRegistration filter =
		// container.addFilter(SPRING_SECURITY_FILTER_CHAIN,
		// new DelegatingFilterProxy(SPRING_SECURITY_FILTER_CHAIN));
		// filter.addMappingForUrlPatterns(null, false, URL_SECURITY_PATTERN);
		
	}

}
