package com.logo.ui.components;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ClickLabel extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	public ClickLabel(String value) {
		Label label = new Label(value, ContentMode.HTML);
		addComponent(label);
	}
}
