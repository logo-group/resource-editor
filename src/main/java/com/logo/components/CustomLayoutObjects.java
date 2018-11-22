package com.logo.components;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.ReLanguageTable;
import com.logo.util.LogoResConstants;
import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;

import eu.michaelvogt.vaadin.attribute.Attribute;

public class CustomLayoutObjects {
	
	Button saveButtonForEdit = new ButtonGenerator(LogoResConstants.SAVESTR);
	Button cancelButtonForEdit = new ButtonGenerator(LogoResConstants.CANCELSTR);

	Button saveButtonForAdd = new ButtonGenerator(LogoResConstants.SAVESTR);
	Button cancelButtonForAdd = new ButtonGenerator(LogoResConstants.CANCELSTR);

	Button translateButton = new ButtonGenerator(LogoResConstants.TRANSLATESTR);
	
	Button addButton = new ButtonGenerator(LogoResConstants.ADDSTR);
	
	TextArea txtArea = new TextArea();
	Binder<ReLanguageTable> binder = new Binder<>(ReLanguageTable.class);
	Attribute spellcheckAttr = new Attribute("spellcheck", "false");
	HorizontalLayout buttonLayoyt = new HorizontalLayout();

	public CustomLayoutObjects() {
		txtArea.addStyleName(MaterialTheme.TEXTAREA_BORDERLESS);
		txtArea.setWordWrap(true);
		spellcheckAttr.extend(txtArea);
		buttonLayoyt.setSizeUndefined();
		buttonLayoyt.setWidth("240px");
		buttonLayoyt.setSpacing(false);
		buttonLayoyt.setMargin(false);
		
		saveButtonForEdit.setWidth("80px");
		cancelButtonForEdit.setWidth("80px");
		saveButtonForAdd.setWidth("80px");
		cancelButtonForAdd.setWidth("80px");
		translateButton.setWidth("80px");
		addButton.setWidth("80px");
	}
}
