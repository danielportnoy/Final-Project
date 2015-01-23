package com.example.logic.placeholder;

public enum PlaceHolderType {

	addNewLine("+"),

	ReturnType("return type"),

	Idnt("idetifier"),
	IntIdnt("int idetifier"),
	BoolIdnt("boolean idetifier"), 

	Expr("expression"),
	IntExpr("int expression"),
	BoolExpr("boolean expression"),

	Semicolon_sign(";"), 
	Equals_sign("="), 
	
	IntLiteral("int literal"),
	BoolLiteral("boolean literal"),
	
	;

	private final String name;       

	private PlaceHolderType(String s) {
		name = s;
	}
	public String toString() {
		return name;
	};

}
