package com.logo.util;

import javax.persistence.AttributeConverter;

public class ResourceCaseConverter implements AttributeConverter<ResourceCase, Integer> {
	@Override
	public Integer convertToDatabaseColumn(ResourceCase resourceCase) {
		switch (resourceCase) {
		case NORESTRICTION:
			return 1;
		case LOWERCASE:
			return 2;
		case UPPERCASE:
			return 3;
		case TITLECASE:
			return 4;
		case SENTENCECASE:
			return 5;
		default:
			throw new IllegalArgumentException("Invalid value " + resourceCase);
		}
	}

	@Override
	public ResourceCase convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case 1:
			return ResourceCase.NORESTRICTION;
		case 2:
			return ResourceCase.LOWERCASE;
		case 3:
			return ResourceCase.UPPERCASE;
		case 4:
			return ResourceCase.TITLECASE;
		case 5:
			return ResourceCase.SENTENCECASE;
		default:
			return ResourceCase.NORESTRICTION;
		}
	}
}
