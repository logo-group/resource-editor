package com.logo.util.enums;

public enum MessageType {
	ERROR(1), INFO(2), WARN(3), SELECTION(4);

	private final Integer typ;

	private MessageType(Integer typ) {
		this.typ = typ;
	}

	@Override
	public String toString() {
		if (typ == 1) {
			return "Error";
		}
		if (typ == 2) {
			return "Info";
		}
		if (typ == 3) {
			return "Warn";
		}		
		if (typ == 4) {
			return "Selection";
		}		
		return "";
	}
}
