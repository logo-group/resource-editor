package com.logo.util.enums;

public enum OwnerProduct {
	INFRASTRUCTURE(-1), APPLICATION(0);

	private final Integer typ;

	private OwnerProduct(Integer typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		if (typ == -1) {
			return "Infrastructure";
		}
		if (typ == 0) {
			return "Application";
		}
		return "";
	}
}
