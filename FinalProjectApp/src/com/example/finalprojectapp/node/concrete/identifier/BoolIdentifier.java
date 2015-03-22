package com.example.finalprojectapp.node.concrete.identifier;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;

public class BoolIdentifier extends Node{
	
	private String name;
	
	public BoolIdentifier(String name) {
		this.name = name;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {
		
		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, name, null));

		return res;
	}
	
	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target,boolean isHighlighted) {
		
		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, name));

		return res;
	}

	@Override
	public ReturnObject run() {	
		LevelManager.getInstance().takeSnapshot(this);
		return new ReturnObject(LevelManager.getInstance().getBooleanValueFromIdentifier(name));
	}

}
