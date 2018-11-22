package com.logo.components;

import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.data.converter.StringToIntegerConverter;

public class NoThousandsStringToIntegerConverter  extends StringToIntegerConverter
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoThousandsStringToIntegerConverter(String errorMessage) {
		super(errorMessage);
	}

	@Override
    protected NumberFormat getFormat( Locale locale )
    {
        NumberFormat format = super.getFormat( locale );
        format.setGroupingUsed( false );
        return format;
    }
}
