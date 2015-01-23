package com.example.logic.pattern;

public class Literal_Ptrn extends Pattern {
	
	private String literal;

	public Literal_Ptrn(String literal) {
		super(0);
		
		this.literal = literal;
	}

	@Override
	public String toString() {
		return literal;
	}

}
