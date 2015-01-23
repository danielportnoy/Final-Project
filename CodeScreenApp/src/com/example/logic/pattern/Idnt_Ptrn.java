package com.example.logic.pattern;


public class Idnt_Ptrn extends Pattern{
	
	private String name;

	public Idnt_Ptrn(String name) {
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
