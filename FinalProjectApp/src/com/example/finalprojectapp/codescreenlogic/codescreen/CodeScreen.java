package com.example.finalprojectapp.codescreenlogic.codescreen;

import java.util.LinkedList;

import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionFilter;
import com.example.finalprojectapp.codescreenlogic.pattern.NewLinePattern;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolderType;

public class CodeScreen {

	private LinkedList<PlaceHolder> statements;

	public CodeScreen() {
		statements = new LinkedList<PlaceHolder>();
		addNewLine();
	}

	public void addNewLine() {
		statements.add(new PlaceHolder(null, new NewLinePattern() , PlaceHolderType.addNewLine ,OptionFilter.Void, InputMethod.Option));
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

	public boolean validateAllLines(){
		for (PlaceHolder PlaceHolder : statements) {
			if(!PlaceHolder.validate())
				return false;
		}

		return true;
	}

}
