package com.example.logic.grammar;

public class ReturnDataType_Ptrn extends Pattern {

	private String type;

	public ReturnDataType_Ptrn(String type ) {
		super(0);	
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}
