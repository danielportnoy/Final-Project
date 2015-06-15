package com.example.finalprojectapp.coderunning.exception.concrete;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.coderunning.exception.MyException;

@SuppressWarnings("serial")
public class DivisionByZeroException extends MyException{

	public DivisionByZeroException() {
		super(Constants.DIVISION_BY_ZERO_EXCEPTION_TEXT);
	}
	

}
