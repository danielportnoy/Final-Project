package com.example.finalprojectapp.codescreenlogic.optionmenu;

public enum OptionEnum {
	
	VarDec ("var dec"),
	VarDecNoAsgn ("var dec no asgn"),
	VarDecWithAsgn ("var dec with asgn"),

	Int ("int"),
	Boolean ("boolean"),
	
	Literal ("literal"),
	
	True ("true"),
	False ("false"),
	
	Function("function"),

	;

	private final String name;       

	private OptionEnum(String s) {
		name = s;
	}
	public String toString() {
		return name;
	};
}
