package com.example.finalprojectapp.codewriting.managment;

import com.example.finalprojectapp.node.Setter;

public class CodeWritingManager {

	private CodeWritingLogicUnit logics;
	private CodeWritingGraphicUnit graphics;

	public CodeWritingManager(CodeWritingLogicUnit logics, CodeWritingGraphicUnit graphics) { 
		this.logics = logics;
		this.graphics = graphics;	
	}

	public void SetterClick(Setter setter) {

		logics.loadOptionsForSetter(setter);

		graphics.getOptionsAdapter().setCurrentSetter(setter);
		graphics.updateOptions();
	}

	public void refresh(){
		logics.getCurrentOptions().clear();
		logics.updateCodeLines();

		graphics.updateOptions();
		graphics.updateCodeLines();
	}
}
