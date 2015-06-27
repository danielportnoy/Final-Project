package com.example.finalprojectapp.node;

/**
 * Hold the data returned from a Run simulation.
 * @author daniel portnoy
 *
 */
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
	}
	
	public Integer getIntValue() {
		return intValue;
	}
	
	public Boolean getBoolValue() {
		return boolValue;
	}
}
