package com.logo.ui.components;

import org.vaadin.teemu.switchui.Switch;

import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.TextField;

@SuppressWarnings("rawtypes")
public class SwitchWithTextBox extends Switch implements ValueChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TextField swcTxtField = new TextField();
	
	public TextField getSwcTxtField() {
		return swcTxtField;
	}
	
	@SuppressWarnings("unchecked")
	public SwitchWithTextBox(String caption,int value)
	{
		super(caption, (value == 1));	
		addValueChangeListener(this);
		setStyleName("compact");
		swcTxtField.setValue("1");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getSource() instanceof Switch) {
			if((boolean) event.getValue())
				swcTxtField.setValue("1");
			else
				swcTxtField.setValue("0");
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
