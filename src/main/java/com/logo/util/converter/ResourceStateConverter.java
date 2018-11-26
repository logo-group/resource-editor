package com.logo.util.converter;

import javax.persistence.AttributeConverter;

import com.logo.util.enums.ResourceState;

public class ResourceStateConverter implements AttributeConverter<ResourceState, Integer> {
	@Override
	public Integer convertToDatabaseColumn(ResourceState resourceState) {
		switch (resourceState) {
		case ACTIVE:
			return 1;
		case INACTIVE:
			return 0;
		default:
			throw new IllegalArgumentException("Invalid value " + resourceState);
		}
	}

	@Override
	public ResourceState convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case 0:
			return ResourceState.INACTIVE;
		case 1:
			return ResourceState.ACTIVE;
		default:
			return ResourceState.ACTIVE;
		}
	}
}
