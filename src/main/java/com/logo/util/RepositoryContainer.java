package com.logo.util;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.vaadin.spring.i18n.MessageProvider;

@Component
public class RepositoryContainer implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient MessageProvider messageProvider;
	private transient Locale appLocale;

	public RepositoryContainer() {
	}

	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}

	public MessageProvider getMessageProvider() {
		return messageProvider;
	}

	public void setAppLocale(Locale appLocale) {
		this.appLocale = appLocale;
	}

	public Locale getAppLocale() {
		return appLocale;
	}

}
