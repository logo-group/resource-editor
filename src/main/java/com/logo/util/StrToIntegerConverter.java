package com.logo.util;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.converter.StringToIntegerConverter;

public class StrToIntegerConverter extends StringToIntegerConverter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StrToIntegerConverter(String errorMessage) {
		super(errorMessage);
	}

	@Override
	protected java.text.NumberFormat getFormat(Locale locale) {
        NumberFormat numberFormat = super.getFormat(locale);
        numberFormat.setGroupingUsed(false);
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        return numberFormat;
    }
}
