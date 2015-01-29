package com.example.finalprojectapp.gamescreenlogic;

import java.util.List;

import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;

public abstract class Scenario {
	
	public abstract void RunCode(List<PlaceHolder> codeLines);

	public abstract boolean checkWin();
	
	public abstract void reset();

	//public abstract List<Pattern> getSpecialPatterns();

}
