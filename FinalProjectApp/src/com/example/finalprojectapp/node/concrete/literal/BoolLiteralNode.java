package com.example.finalprojectapp.node.concrete.literal;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Type;

public class BoolLiteralNode extends Node {

	private boolean value;

	public BoolLiteralNode(boolean value) {
		this.value = value;
		setType(Type.Bool);
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, value + "", null));

		return res;
	}
	
	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {
		
		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, value + ""));

		return res;
	}

	
	@Override
	public ReturnObject run() {	
		LevelManager.getInstance().takeSnapshot(this);
		return new ReturnObject(value);
	}

}
