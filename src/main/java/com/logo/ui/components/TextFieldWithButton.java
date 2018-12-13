package com.logo.ui.components;

import com.github.appreciated.material.MaterialTheme;
import com.logo.util.LangHelper;
import com.logo.util.LogoResConstants;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TextFieldWithButton extends HorizontalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final TextField textField;
	private final Button button;

	public TextFieldWithButton(String caption, Resource icon) {
		setWidth("100%");
		setSpacing(false);
		setCaption(caption);

		textField = new TextField();
		textField.setPlaceholder(LangHelper.getLocalizableMessage(LogoResConstants.SEARCHSTR));
		textField.addStyleName(ValoTheme.TEXTFIELD_SMALL);
		textField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		textField.setIcon(VaadinIcons.SEARCH);
		textField.setWidth("100%");

		button = new Button(icon);
		button.addStyleName(MaterialTheme.BUTTON_ICON_ONLY);
		button.addStyleName(MaterialTheme.BUTTON_SMALL);

		addComponent(textField);
		setExpandRatio(textField, 1.0f);
		setComponentAlignment(textField, Alignment.MIDDLE_LEFT);
	}

	public TextField getTextField() {
		return textField;
	}

	public Button getButton() {
		return button;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
