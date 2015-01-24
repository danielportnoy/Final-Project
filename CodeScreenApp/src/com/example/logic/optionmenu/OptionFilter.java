package com.example.logic.optionmenu;


public enum OptionFilter {
	
	ReturnType ("return type"),
	
	Void ("void"),
	Int ("int"),
	Boolean ("boolean"),
	
	BooleanLiteral("boolean literal");
	
	;
	
	private final String name;       

	private OptionFilter(String s) {
		name = s;
	}
	public String toString() {
		return name;
	};
}
