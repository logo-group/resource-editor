package com.logo.ui.components;

import java.util.Locale;

import com.github.appreciated.material.MaterialTheme;
import com.logo.LogoresMainUI;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;

public class ButtonGenerator extends Button {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonGenerator(String buttonCaption) {
		if (buttonCaption.equals(LogoResConstants.EDITSTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_FRIENDLY);
			setIcon(VaadinIcons.EDIT);
		}
		if (buttonCaption.equals(LogoResConstants.ADDSTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
			setIcon(VaadinIcons.PLUS);
		}
		if (buttonCaption.equals(LogoResConstants.ADDNEWSTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_CUSTOM);
			setIcon(VaadinIcons.PLUS);
		}
		if (buttonCaption.equals(LogoResConstants.SAVESTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_FRIENDLY);
			setIcon(VaadinIcons.CHECK);
		}
		if (buttonCaption.equals(LogoResConstants.SAVEANDNEWSTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " " + MaterialTheme.BUTTON_FRIENDLY);
			setIcon(VaadinIcons.FORWARD);
		}
		if (buttonCaption.equals(LogoResConstants.CANCELSTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND);
			setIcon(VaadinIcons.CLOSE);
		}
		if (buttonCaption.equals(LogoResConstants.CLOSELSTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND);
			setIcon(VaadinIcons.CLOSE_BIG);
		}
		if (buttonCaption.equals(LogoResConstants.DELETESTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " "+ MaterialTheme.BUTTON_DANGER);
			setIcon(VaadinIcons.TRASH);
		}
		if (buttonCaption.equals(LogoResConstants.TRANSLATESTR)) {
			addStyleName(MaterialTheme.BUTTON_BORDER + " " + MaterialTheme.BUTTON_ROUND + " "+ MaterialTheme.BUTTON_ICON_ALIGN_TOP + " "+MaterialTheme.BUTTON_CUSTOM);
			setIcon(VaadinIcons.EXCHANGE);
		}
		String cap = LangHelper.getLocalizableMessage(buttonCaption);
		setDescription(cap);
		setHeight("30px");
	}
}
