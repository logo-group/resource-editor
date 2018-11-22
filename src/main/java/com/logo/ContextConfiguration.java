package com.logo;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

	private static DataSource dataSource; 
	
	@Bean
	@Scope(WebApplicationContext.SCOPE_SESSION)
	public static  UISessionState getUISessionState() {
		return new UISessionState();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource primaryDataSource() {
		dataSource = DataSourceBuilder.create().build();
		return dataSource;
	}

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	public static DataSource getDataSource()
	{
		return dataSource;		
	}
}
