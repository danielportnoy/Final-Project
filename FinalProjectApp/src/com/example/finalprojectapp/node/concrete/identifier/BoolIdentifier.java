package com.example.finalprojectapp.node.concrete.identifier;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;

public class BoolIdentifier extends Node{
	
	private String name;
	
	public BoolIdentifier(String name) {
		this.name = name;
	}

	@Override
	public List<CodePart> getCodeParts() {
		
		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, name, null));

		return res;
	}
	
	@Override
	public ReturnObject run() {	
		return new ReturnObject(LevelManager.getInstance().getCodeRunningManager().getBool(name));
	}

}
