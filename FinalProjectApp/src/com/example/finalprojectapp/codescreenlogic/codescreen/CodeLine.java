package com.example.finalprojectapp.codescreenlogic.codescreen;

import java.util.ArrayList;
import java.util.List;

public class CodeLine {

	private List<CodePart> CodeScreenParts;

	public CodeLine() {
		CodeScreenParts = new ArrayList<CodePart>();
	}

	public List<CodePart> getCodeScreenParts() {
		return CodeScreenParts;
	}

}
