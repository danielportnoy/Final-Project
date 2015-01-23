package com.example.logic.grammar;

public class BooleanIdnt_Ptrn extends Pattern{
	
	private String name;

	public BooleanIdnt_Ptrn(String name) {
		super(0);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
