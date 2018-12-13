package com.logo.util.enums;

public enum ResourceCase {

	NORESTRICTION(1), LOWERCASE(2), UPPERCASE(3), TITLECASE(4), SENTENCECASE(5);

	private final Integer typ;

	private ResourceCase(Integer typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		if (typ == 1) {
			return "NoRestriction";
		}
		if (typ == 2) {
			return "Lowercase";
		}
		if (typ == 3) {
			return "Uppercase";
		}
		if (typ == 4) {
			return "Titlecase";
		}
		if (typ == 5) {
			return "Sentencecase";
		}
		return "";
	}
}
