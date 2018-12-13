package com.logo.util.enums;

public enum DocType {

	LOCALIZABLEHELP(1), NONLOCALIZABLEHELP(2), REUSABLEHELP(3), LOCALIZABLEJAVA(4);

	private final Integer typ;

	private DocType(Integer typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		if (typ == 1) {
			return "Localizable Help Page";
		}
		if (typ == 2) {
			return "Non-Localizable Help Page";
		}
		if (typ == 3) {
			return "Reusable Paragraph";
		}
		if (typ == 4) {
			return "Localizable Javascript File";
		}
		return "";
	}
}
