package com.logo.components;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ClickLabel extends HorizontalLayout {
	
	public ClickLabel(String value) {

		Label label = new Label (value, ContentMode.HTML);
		addComponent(label);	
	}
}
