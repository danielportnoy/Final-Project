package com.example.logic.codescreen;

import java.util.LinkedList;

import com.example.logic.grammar.PlaceHolder;
import com.example.logic.optionmenu.OptionEnum;
import com.example.logic.optionmenu.OptionFilter;

public class CodeScreen {

	private LinkedList<PlaceHolder> statements;

	public CodeScreen() {
		statements = new LinkedList<PlaceHolder>();
		addNewLine();
	}

	public void addNewLine() {

		/*	PlaceHolder newPH = new PlaceHolder();
		newPH.setOptionFilter(new OptionFilter(null, OptionEnum.Void));*/

		statements.add(new PlaceHolder(new OptionFilter(OptionEnum.Void) , InputMethod.Option , null));
	} 

	public LinkedList<PlaceHolder> getStatements() {
		return statements;
	}

	public void setStatements(LinkedList<PlaceHolder> statements) {
		this.statements = statements;
	}

	public void displayCode(){
		for (PlaceHolder PlaceHolder : statements) {
			System.out.println(PlaceHolder);
		}
	}

	public boolean isAllLinesOK(){
		for (PlaceHolder PlaceHolder : statements) {
			if(!PlaceHolder.validate())
				return false;
		}

		return true;
	}

}
