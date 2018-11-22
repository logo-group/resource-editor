package com.logo.util;

import javax.persistence.AttributeConverter;

public class ResourceTypeConverter implements AttributeConverter<ResourceType, Integer> {
	@Override
	public Integer convertToDatabaseColumn(ResourceType resourceType) {
		switch (resourceType) {
		case LOCALIZABLE:
			return 1;
		case NONLOCALIZABLE:
			return 2;
		default:
			throw new IllegalArgumentException("Invalid value " + resourceType);
		}
	}

	@Override
	public ResourceType convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case 1:
			return ResourceType.LOCALIZABLE;
		case 2:
			return ResourceType.NONLOCALIZABLE;
		default:
			return ResourceType.LOCALIZABLE;
		}
	}
}
