package com.example.finalprojectapp.nodetree.node.literal;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.node.Node;

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

}
