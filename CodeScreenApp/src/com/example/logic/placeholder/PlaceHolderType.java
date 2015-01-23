package com.example.logic.placeholder;

public enum PlaceHolderType {
	
	addNewLine("+"),
	
	ReturnType("return type"),
	
	Idnt("idetifier"),
	IntIdnt("int idetifier"),
	BoolIdnt("boolean idetifier"), 
	
	Semicolon(";"),

	;
	
	private final String name;       

	private PlaceHolderType(String s) {
		name = s;
	}
	public String toString() {
		return name;
	};

}
