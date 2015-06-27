package com.example.finalprojectapp.codewriting.codewriting_components;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the data for displaying a code line.
 * @author daniel portnoy
 *
 */
public class CodeWritingLine {

	private List<CodeWritingPart> CodeWritingParts;

	public CodeWritingLine() {
		CodeWritingParts = new ArrayList<CodeWritingPart>();
	}

	public List<CodeWritingPart> getCodeWritingParts() {
		return CodeWritingParts;
	}

}
