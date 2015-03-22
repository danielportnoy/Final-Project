package com.example.finalprojectapp.coderunning.coderunning_components;

import java.util.ArrayList;
import java.util.List;

public class CodeRunningLine {

	private List<CodeRunningPart> CodeRunningParts;

	public CodeRunningLine() {
		CodeRunningParts = new ArrayList<CodeRunningPart>();
	}

	public List<CodeRunningPart> getCodeRunningParts() {
		return CodeRunningParts;
	}

}
