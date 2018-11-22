package com.logo.util;

public enum ResourceGroup {
		UN("Unity"),
		HR("HR"),
		UNRP("Unity Reporting"),
		HRRP("HR Reporting"),
	    SS("Self Service"),
	    HELP("Help"),
	    MISC("Miscellaneous"),
	    CPORTAL("CPORTAL"),
	    ERP("ERP"),
	    GRC("GRC"),
	    PLATFORM("PLATFORM"),
	    SOHO("SOHO");
	
	private String name;
	    
	private ResourceGroup(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
