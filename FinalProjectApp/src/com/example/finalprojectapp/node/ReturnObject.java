package com.example.finalprojectapp.node;

public class ReturnObject {

	private Integer intValue = null;
	private Boolean boolValue = null;

	public ReturnObject(int intValue) {
		this.intValue = intValue;
	}
	public ReturnObject(boolean boolValue) {
		this.boolValue = boolValue;
	}
	public ReturnObject() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIntValue() {
		return intValue;
	}
	
	public Boolean getBoolValue() {
		return boolValue;
	}
}
