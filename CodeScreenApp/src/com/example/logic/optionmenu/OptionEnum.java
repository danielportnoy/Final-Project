package com.example.logic.optionmenu;

public enum OptionEnum {
	
	VarDec ("var dec"),
	VarDecNoAsgn ("var dec no asgn"),

	Int ("int"),
	Boolean ("boolean"),

	;

	private final String name;       

	private OptionEnum(String s) {
		name = s;
	}
	public String toString() {
		return name;
	};
}
