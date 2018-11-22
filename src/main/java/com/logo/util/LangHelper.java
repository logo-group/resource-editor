package com.logo.util;

import java.text.MessageFormat;
import java.util.Locale;

import org.vaadin.spring.i18n.MessageProvider;

import com.logo.LogoresMainUI;

public class LangHelper {

	private static MessageProvider messageProvider = LogoresMainUI.getMrepositorycontainer().getMessageProvider();

	private LangHelper() {
	}

	public static String getLocalizableMessage(String name) {
		Locale appLocale = LogoresMainUI.getMrepositorycontainer().getAppLocale();
		if(appLocale == null)
			appLocale = new Locale("tr-TR");
		MessageFormat mf = messageProvider.resolveCode(name, appLocale);
		if (mf == null)
			return name;
		else
			return mf.toPattern();
	}
	
}
