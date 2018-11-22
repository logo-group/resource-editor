package com.logo.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		final ServletContext servletContext = event.getSession()
				.getServletContext();
		final WebApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		ApplicationContextLocator.setApplicationContext(context);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ApplicationContextLocator.setApplicationContext(null);
	}

}
