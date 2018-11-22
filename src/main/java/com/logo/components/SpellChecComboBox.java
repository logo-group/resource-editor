package com.logo.components;

import com.logo.util.LogoResConstants;
import com.vaadin.ui.ComboBox;

import eu.michaelvogt.vaadin.attribute.Attribute;

public class SpellChecComboBox<T> extends ComboBox<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpellChecComboBox(String caption) 
	{
		Attribute spellcheckAttr1 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		this.setCaption(caption);
		this.setTextInputAllowed(false);
		spellcheckAttr1.extend(this);
	}

	public SpellChecComboBox(String caption,boolean isHtmlMode) 
	{
		Attribute spellcheckAttr1 = new Attribute(LogoResConstants.SPELLCHECK, LogoResConstants.FALSESTR);
		this.setCaption(caption);
		this.setCaptionAsHtml(true);
		this.setTextInputAllowed(false);
		spellcheckAttr1.extend(this);
	}

}
