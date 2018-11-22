package com.logo.util;

import javax.persistence.AttributeConverter;

public class UserTypeConverter implements AttributeConverter<UserType, Integer> {
	@Override
	public Integer convertToDatabaseColumn(UserType userType) {
		switch (userType) {
		case ADMINISTRATOR:
			return 0;
		case PROGRAMMER:
			return 1;
		case INTERNALLOCALIZER:
			return 2;
		case EXTERNALLOCALIZER:
			return 3;
		default:
			throw new IllegalArgumentException("Invalid value " + userType);
		}
	}

	@Override
	public UserType convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case 0:
			return UserType.ADMINISTRATOR;
		case 1:
			return UserType.PROGRAMMER;
		case 2:
			return UserType.INTERNALLOCALIZER;
		case 3:
			return UserType.EXTERNALLOCALIZER;
		default:
			return UserType.PROGRAMMER;
		}
	}
}
