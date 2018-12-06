package com.logo.ui.components;

import com.github.appreciated.material.MaterialTheme;
import com.logo.data.entity.ReStandard;
import com.logo.util.LogoResConstants;
import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;

import eu.michaelvogt.vaadin.attribute.Attribute;

public class CustomLayoutStandardObjects {

	Button saveButtonForEdit = new ButtonGenerator(LogoResConstants.SAVESTR);
	Button cancelButtonForEdit = new ButtonGenerator(LogoResConstants.CANCELSTR);

	Button saveButtonForAdd = new ButtonGenerator(LogoResConstants.SAVESTR);
	Button cancelButtonForAdd = new ButtonGenerator(LogoResConstants.CANCELSTR);

	TextArea txtArea = new TextArea();
	Binder<ReStandard> binder = new Binder<>(ReStandard.class);
	Attribute spellcheckAttr = new Attribute("spellcheck", "false");
	HorizontalLayout buttonLayoyt = new HorizontalLayout();

	public CustomLayoutStandardObjects() {
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
	}

}
