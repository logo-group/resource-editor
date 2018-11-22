package com.logo.util;

public enum UserType {
	ADMINISTRATOR(0), PROGRAMMER(1), INTERNALLOCALIZER(2), EXTERNALLOCALIZER(3);

	private final Integer typ;

	private UserType(Integer typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		if (typ == 0) {
			return "Administrator";
		}
		if (typ == 1) {
			return "Programmer";
		}
		if (typ == 2) {
			return "Internal Localizer";
		}		
		if (typ == 3) {
			return "External Localizer";
		}		
		return "";
	}
}
