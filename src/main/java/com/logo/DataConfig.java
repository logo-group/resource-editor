package com.logo;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = { "com.logo.data.repository" })
@ComponentScan(basePackages = { "com.logo.data.repository" })
@PropertySource("classpath:application.properties")
public class DataConfig {

	@Bean
	public DataSource dataSource(Environment env) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter adapter,
			Properties additionalProperties) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("com.logo.data.entity");
		emf.setJpaVendorAdapter(adapter);
		emf.setJpaProperties(additionalProperties);
		return emf;
	}

	@Bean
	@ConfigurationProperties
	public Properties additionalProperties(Environment env) {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.current_session_context_class", "thread");
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		properties.setProperty("hibernate.connection.provider_class",
				"org.hibernate.hikaricp.internal.HikariCPConnectionProvider");
		properties.setProperty("hibernate.hikari.dataSourceClassName", env.getProperty("resource.editor.datasource"));
		properties.setProperty("hibernate.hikari.dataSource.url", env.getProperty("spring.datasource.url"));
		properties.setProperty("hibernate.hikari.dataSource.user", env.getProperty("spring.datasource.username"));
		properties.setProperty("hibernate.hikari.dataSource.password", env.getProperty("spring.datasource.password"));
		properties.setProperty("hibernate.hikari.maximumPoolSize", "50");
		properties.setProperty("hibernate.hikari.connectionTimeout", "30000");
		properties.setProperty("hibernate.hikari.maxLifetime", "600000");
		properties.setProperty("hibernate.id.new_generator_mappings", "false"); // To prevent Invalid object name
																				// 'hibernate_sequence'.
		return properties;
	}

	@Bean
	public JpaVendorAdapter adapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}

	@Bean
	public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(entityManagerFactory.getObject());
		return jtm;
	}

}
