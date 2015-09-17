package com.github.ivos.springrest;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		context.register(ApplicationConfiguration.class);

		context.setServletContext(servletContext);
		Dynamic servletRegistration = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		servletRegistration.addMapping("/");
		servletRegistration.setLoadOnStartup(1);
	}

}
