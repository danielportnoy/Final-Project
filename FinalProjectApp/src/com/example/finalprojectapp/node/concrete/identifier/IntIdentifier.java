package com.example.finalprojectapp.node.concrete.identifier;

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

public class IntIdentifier extends Node{

	private String name;

	public IntIdentifier(String name) {
		this.name = name;
		setType(Type.Int);
	}

	@Override
	public boolean addChild(Node child, int order) {
		return false;
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
		res.add(name);
		return res;
	}

	@Override
	public Node getFirstNode() {
		return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, name, null, this));

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
	public ReturnObject run() throws MyException {	
		//LevelManager.getInstance().takeSnapshot(this); //TODO
		return new ReturnObject(LevelManager.getInstance().getInegerValueFromIdentifier(name));
	}
}
