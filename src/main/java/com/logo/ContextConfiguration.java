package com.logo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.WebApplicationContext;

import com.logo.util.UISessionState;
import com.vaadin.spring.annotation.EnableVaadinNavigation;

@Configuration
@EnableVaadinNavigation
@EnableJpaAuditing
public class ContextConfiguration {

	@Bean
	@Scope(WebApplicationContext.SCOPE_SESSION)
	public static UISessionState getUISessionState() {
		return new UISessionState();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
