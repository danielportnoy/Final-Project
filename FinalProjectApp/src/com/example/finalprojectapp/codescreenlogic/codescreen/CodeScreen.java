package com.example.finalprojectapp.codescreenlogic.codescreen;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.nodetree.node.block.InitialBlockNode;

public class CodeScreen {

	private InitialBlockNode initialBlock;
	private List<CodeLine> codeLines;

	public CodeScreen() {
		initialBlock = new InitialBlockNode();
		codeLines = new ArrayList<CodeLine>();
		updateCodeLines();
	}

	public void updateCodeLines() {

		codeLines.clear();

		CodeLine temp = new CodeLine();

		for (CodePart codePart : initialBlock.getCodeParts()) {
			if(!codePart.isNewline())
				temp.getCodeScreenParts().add(codePart);
			else{
				codeLines.add(temp);
				temp = new CodeLine();
			}
		}	

		codeLines.add(temp);
	}

	public InitialBlockNode getInitialBlock() {
		return initialBlock;
	}

	public List<CodeLine> getCodeLines() {
		return codeLines;
	}

	public boolean isCodeScreenValid() {
		for (CodePart codePart : initialBlock.getCodeParts())
			if(codePart.getSetter()!=null && codePart.getSetter().isMandatory())
				return false;

		return true;
	}

}
