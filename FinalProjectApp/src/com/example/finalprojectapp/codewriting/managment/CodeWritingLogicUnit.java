package com.example.finalprojectapp.codewriting.managment;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codeline.CodeLine;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.codewriting.option.concrete.identifier.BoolIdentifierOption;
import com.example.finalprojectapp.codewriting.option.concrete.identifier.IntIdentifierOption;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class CodeWritingLogicUnit {

	private List<CodeLine> codeLines;
	private List<Option> currentOptions;

	public CodeWritingLogicUnit() {
		codeLines = new ArrayList<CodeLine>();
		currentOptions = new ArrayList<Option>();
	}

	public List<CodeLine> getCodeLines() {
		return codeLines;
	}

	public List<Option> getCurrentOptions() {
		return currentOptions;
	}

	public void updateCodeLines() {

		codeLines.clear();

		CodeLine temp = new CodeLine();

		for (CodePart codePart : LevelManager.getInstance().getRootNode().getCodeParts()) {
			if(!codePart.isNewline())
				temp.getCodeScreenParts().add(codePart);
			else{
				codeLines.add(temp);
				temp = new CodeLine();
			}
		}	

		codeLines.add(temp);
	}

	public void loadOptionsForSetter(Setter setter) {

		currentOptions.clear();

		loadStaticOptions(setter);
		loadIdentifierOptions(setter);
	}

	private void loadStaticOptions(Setter setter) {

		for (Option option : LevelManager.getInstance().getScenario().getAvailableOptions()) {
			for (Type type : setter.possibleTypes()) {
				if(option.isType(type))
					currentOptions.add(option);
			}		
		}
	}

	private void loadIdentifierOptions(Setter setter) {

		List<String> identifiers;

		if(setter.possibleTypes().contains(Type.Bool)){
			identifiers = Scope.getBooleanIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				currentOptions.add(new BoolIdentifierOption(id));
		}
		if(setter.possibleTypes().contains(Type.Int)){
			identifiers = Scope.getIntegerIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				currentOptions.add(new IntIdentifierOption(id));
		}
	}

}
