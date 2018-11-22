package com.logo.util;

import javax.persistence.AttributeConverter;

public class MessageTypeConverter implements AttributeConverter<MessageType, Integer> {
	@Override
	public Integer convertToDatabaseColumn(MessageType messageType) {
		switch (messageType) {
		case ERROR:
			return 1;
		case INFO:
			return 2;
		case WARN:
			return 3;
		case SELECTION:
			return 4;
		default:
			throw new IllegalArgumentException("Invalid value " + messageType);
		}
	}

	@Override
	public MessageType convertToEntityAttribute(Integer dbValue) {
		switch (dbValue) {
		case 1:
			return MessageType.ERROR;
		case 2:
			return MessageType.INFO;
		case 3:
			return MessageType.WARN;
		case 4:
			return MessageType.SELECTION;
		default:
			return MessageType.ERROR;
		}
	}
}
