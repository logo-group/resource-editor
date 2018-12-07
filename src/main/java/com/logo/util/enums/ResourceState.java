package com.logo.util.enums;

public enum ResourceState {
	ACTIVE(1), INACTIVE(0);

	private final Integer typ;

	private ResourceState(Integer typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		if (typ == 0) {
			return "InActive";
		}
		if (typ == 1) {
			return "Active";
		}
		return "";
	}

	public Integer getTyp() {
		return typ;
	}

}
