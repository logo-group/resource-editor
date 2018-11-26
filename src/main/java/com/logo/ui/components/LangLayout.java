package com.logo.ui.components;

import org.vaadin.teemu.switchui.Switch;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@SuppressWarnings("rawtypes")
public class LangLayout extends HorizontalLayout implements ValueChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int READ = 1;
	private static final int WRITE = 2;
	private static final int DELETE = 4;
	private static final String COMPACT = "compact";

	private Switch sRead;
	private Switch sWrite;
	private Switch sDelete;
	private TextField langTxtField = new TextField();

	public Switch getSRead() {
		return sRead;
	}

	public Switch getSWrite() {
		return sWrite;
	}

	public Switch getSDelete() {
		return sDelete;
	}

	public TextField getLangTxtField() {
		return langTxtField;
	}

	public LangLayout(String lang, int value) {
		setWidth("100%");
		setHeight("100%");
		addStyleName(MaterialTheme.CARD_1);
		Label caption = new Label(lang);
		caption.setHeight("35px");

		sRead = createSwitch("Read", COMPACT, isChecked(READ, value));
		sWrite = createSwitch("Write", COMPACT, isChecked(WRITE, value));
		sDelete = createSwitch("Delete", COMPACT, isChecked(DELETE, value));

		addComponent(caption);
		addComponent(sRead);
		addComponent(sWrite);
		addComponent(sDelete);
	}

	private boolean isChecked(int flag, int value) {
		boolean val = false;
		if ((value & READ) == flag)
			return true;
		if ((value & WRITE) == flag)
			return true;
		if ((value & DELETE) == flag)
			return true;
		return val;
	}

	public void setChecked(boolean read, boolean write, boolean delete) {
		int value = 0;
		if (read)
			value += READ;
		if (write)
			value += WRITE;
		if (delete)
			value += DELETE;
		langTxtField.setValue(Integer.toString(value));
	}

	public Switch createSwitch(String caption, String style, boolean initialState) {
		Switch switchComponent = new Switch(caption, initialState);
		switchComponent.addValueChangeListener(this);
		if (style != null) {
			switchComponent.setStyleName(style);
		}
		return switchComponent;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getSource() instanceof Switch) {
			/**
			Notification.show(event.getComponent().getCaption() + ": " + event.getValue());
			**/
		}
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
