package com.logo;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class LogoresApplication {

	private static ConfigurableListableBeanFactory beanFactory;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LogoresApplication.class, args);
		beanFactory = context.getBeanFactory();
	}

	public static ConfigurableListableBeanFactory getBeanFactory() {
		return beanFactory;
	}

}
