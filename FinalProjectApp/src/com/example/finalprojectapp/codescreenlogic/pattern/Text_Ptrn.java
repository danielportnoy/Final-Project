package com.example.finalprojectapp.codescreenlogic.pattern;

public class Text_Ptrn extends Pattern{
	
	private String text;

	public Text_Ptrn(String text) {
		super(0);
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
