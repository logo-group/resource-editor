package com.logo.util;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.vaadin.spring.i18n.CompositeMessageSource;
import org.vaadin.spring.i18n.MessageProvider;
import org.vaadin.spring.i18n.MessageProviderCacheCleanupExecutor;
import org.vaadin.spring.i18n.ResourceBundleMessageProvider;

public class ApplicationContextLocator {

	private static Logger logger = Logger.getLogger(ApplicationContextLocator.class.getName());
	private static final Object monitor = new Object();
	protected static ApplicationContext applicationContext;
	protected static Collection<URL> webFlowConfiguration;

	@PropertySource("classpath:application.properties")
	static class ApplicationProperties {
	}

	private ApplicationContextLocator() {
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		ApplicationContextLocator.applicationContext = applicationContext;
		synchronized (monitor) {
			monitor.notifyAll();
		}
	}

	public static MessageProvider messageProvider() {
		ResourceBundle.clearCache();
		return new ResourceBundleMessageProvider("ApplicationResources");
	}

	@Bean
	public static MessageProviderCacheCleanupExecutor messageProviderCacheCleanupExecutor(Environment environment,
			CompositeMessageSource messageSource) {
		return new MessageProviderCacheCleanupExecutor(environment, messageSource);
	}

	public static ApplicationContext getApplicationContext() {
		try {
			synchronized (monitor) {
				while (ApplicationContextLocator.applicationContext == null) {
					monitor.wait();
				}
			}
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, "Interrupted!", e);
			Thread.currentThread().interrupt();
		}
		return ApplicationContextLocator.applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T getBean(String beanId) {
		return (T) getApplicationContext().getBean(beanId);
	}

	public static ApplicationContext getApplicationContextOrNull() {
		return ApplicationContextLocator.applicationContext;
	}
}
