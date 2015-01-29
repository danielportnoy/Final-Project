package com.example.finalprojectapp.codescreenlogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Scope {

	private Map<String, Integer> IntegerScope;
	private Map<String, Boolean> BooleanScope;

	public Scope() {
		IntegerScope = new HashMap<String, Integer>();
		BooleanScope = new HashMap<String, Boolean>();
	}

	public Map<String, Integer> getIntegerScope() {
		return IntegerScope;
	}

	public Map<String, Boolean> getBooleanScope() {
		return BooleanScope;
	}

	public static Scope clone(Scope other){

		Scope clone = new Scope();

		for (Entry<String, Integer> entry : other.getIntegerScope().entrySet()) {
			clone.getIntegerScope().put(entry.getKey(), entry.getValue());
		}
		for (Entry<String, Boolean> entry : other.getBooleanScope().entrySet()) {
			clone.getBooleanScope().put(entry.getKey(), entry.getValue());
		}

		return clone;
	}
}
