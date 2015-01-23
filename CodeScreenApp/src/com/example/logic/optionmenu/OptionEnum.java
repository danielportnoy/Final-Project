package com.example.logic.optionmenu;

public enum OptionEnum {
	
	VarDec ("var dec"),
	VarDecNoAsgn ("var dec no asgn"),

	ReturnType ("return type"),
	Void ("void"),
	Int ("int"),
	Boolean ("boolean"),
	
	Idnt("idetifier"),//TODO
	IntIdnt ("int identifier"),
	BooleanIdnt ("boolean identifier"),

	;

	private final String name;       

	private OptionEnum(String s) {
		name = s;
	}
	public String toString() {
		return name;
	};
}
