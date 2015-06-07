package com.example.finalprojectapp.node.concrete.operators.arithmetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Type;

public class IncrementNode extends Node {

	private String identifierName;

	public IncrementNode(String identifierName) {
		this.identifierName = identifierName;
		setType(Type.Int);
	}
	
	@Override
	public List<Node> getChildNodes() {
		return null;
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {
		return true;
	}
	
	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		return res;
	}
	
	@Override
	public Set<String> getUsedIdentifiers() {
		
		HashSet<String> res = new HashSet<String>();
		res.add(identifierName);
		return res;
	}
	
	@Override
	public Node getFirstNode() {
		return null;
	}
	
	@Override
	public List<CodeWritingPart> getCodeWritingParts() {
		
		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, identifierName+"++", null, this));

		if(!isHideSemicolon())
			res.add(new CodeWritingPart(false, false, ";", null, this));
		
		return res;
	}
	
	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, identifierName+"++"));

		if(!isHideSemicolon())
			res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		return res;
	}
	
	@Override
	public ReturnObject run() throws MyException {
		LevelManager.getInstance().takeSnapshot(this);
		
		int result = LevelManager.getInstance().getInegerValueFromIdentifier(identifierName) + 1;
		
		LevelManager.getInstance().putIntegerValueToIdentifier(identifierName, result);
		return new ReturnObject(result);
	}

}
