package com.logo.util.converter;

import javax.persistence.AttributeConverter;

import com.logo.util.enums.DocType;

public class DocTypeConverter implements AttributeConverter<DocType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(DocType docType) {
		switch (docType) {
		case LOCALIZABLEHELP:
			return 1;
		case NONLOCALIZABLEHELP:
			return 2;
		case REUSABLEHELP:
			return 3;
		case LOCALIZABLEJAVA:
			return 4;
		default:
			throw new IllegalArgumentException("Invalid value " + docType);
		}
	}

	@Override
	public DocType convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case 1:
			return DocType.LOCALIZABLEHELP;
		case 2:
			return DocType.NONLOCALIZABLEHELP;
		case 3:
			return DocType.REUSABLEHELP;
		case 4:
			return DocType.LOCALIZABLEJAVA;
		default:
			return DocType.LOCALIZABLEHELP;
		}
	}
}
