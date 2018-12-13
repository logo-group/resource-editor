package com.logo.util.converter;

import javax.persistence.AttributeConverter;

import com.logo.util.enums.OwnerProduct;

public class OwnerProductConverter implements AttributeConverter<OwnerProduct, Integer> {

	@Override
	public Integer convertToDatabaseColumn(OwnerProduct ownerProduct) {
		switch (ownerProduct) {
		case INFRASTRUCTURE:
			return -1;
		case APPLICATION:
			return 0;
		default:
			throw new IllegalArgumentException("Invalid value " + ownerProduct);
		}
	}

	@Override
	public OwnerProduct convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case -1:
			return OwnerProduct.INFRASTRUCTURE;
		case 0:
			return OwnerProduct.APPLICATION;
		default:
			return OwnerProduct.INFRASTRUCTURE;
		}
	}
}
