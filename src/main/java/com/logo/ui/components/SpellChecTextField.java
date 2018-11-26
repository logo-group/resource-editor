package com.logo.ui.components;

import com.logo.util.LogoResConstants;
import com.vaadin.ui.TextField;

import eu.michaelvogt.vaadin.attribute.Attribute;

public class SpellChecTextField extends TextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpellChecTextField(String caption) 
	{
		Attribute spellcheckAttr1 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		this.setCaption(caption);
		spellcheckAttr1.extend(this);
	}
	@Override
	public void setValue(String value) {
		if(value == null)
			super.setValue("");
		else
			super.setValue(value);
	}
}
