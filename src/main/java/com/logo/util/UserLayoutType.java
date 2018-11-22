package com.logo.util;

public enum UserLayoutType {
		V("Vertical"),
		H("Horizontal");
	
	private String type;
	    
	private UserLayoutType(String type)
	{
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
