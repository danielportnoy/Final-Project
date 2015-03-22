package com.example.finalprojectapp.codewriting.codewriting_components;

import java.util.ArrayList;
import java.util.List;

public class CodeWritingLine {

	private List<CodeWritingPart> CodeWritingParts;

	public CodeWritingLine() {
		CodeWritingParts = new ArrayList<CodeWritingPart>();
	}

	public List<CodeWritingPart> getCodeWritingParts() {
		return CodeWritingParts;
	}

}
