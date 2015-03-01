package com.example.finalprojectapp.nodetree.node.identifier;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.node.Node;

public class IntIdentifier extends Node{
	
	private String name;
	
	public IntIdentifier(String name) {
		this.name = name;
	}

	@Override
	public List<CodePart> getCodeParts() {
		
		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, name, null));

		return res;
	}

}
