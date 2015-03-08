package com.example.finalprojectapp.node.concrete.literal;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;

public class IntLiteralNode extends Node {

	private int value;

	public IntLiteralNode(int value) {
		this.value = value;
	}

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, value + "", null));

		return res;
	}
	
	@Override
	public ReturnObject run() {	
		return new ReturnObject(value);
	}

}
