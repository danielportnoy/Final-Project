package com.example.finalprojectapp.codewriting.managment;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingLine;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.codewriting.option.concrete.identifier.BoolIdentifierOption;
import com.example.finalprojectapp.codewriting.option.concrete.identifier.IntIdentifierOption;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class CodeWritingLogicUnit {

	private List<CodeWritingLine> codeWritingLines;
	private List<Option> currentOptions;

	public CodeWritingLogicUnit() {
		codeWritingLines = new ArrayList<CodeWritingLine>();
		currentOptions = new ArrayList<Option>();
	}

	public List<CodeWritingLine> getWritingCodeLines() {
		return codeWritingLines;
	}

	public List<Option> getCurrentOptions() {
		return currentOptions;
	}

	public void updateCodeWritingLines() {

		codeWritingLines.clear();

		CodeWritingLine temp = new CodeWritingLine();

		for (CodeWritingPart codePart : LevelManager.getInstance().getRootNode().getCodeWritingParts()) {
			if(!codePart.isNewline())
				temp.getCodeWritingParts().add(codePart);
			else{
				codeWritingLines.add(temp);
				temp = new CodeWritingLine();
			}
		}	

		codeWritingLines.add(temp);
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

	/*public boolean isCodeLinesValid() {
		for (CodeWritingPart codePart : LevelManager.getInstance().getRootNode().getCodeWritingParts())
			if(codePart.getSetter()!=null && codePart.getSetter().isMandatory())
				return false;

		return true;
	}*/

}
