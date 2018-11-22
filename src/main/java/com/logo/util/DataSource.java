package com.logo.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSource {

	 private static final List<String> PLANETS = Arrays
	            .asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
	"Uranus", "Neptune");

	public static List<String> getPlanets() {
	        return Collections.unmodifiableList(PLANETS);
	}
	
	private DataSource() {
	}
}
