package com.example.finalprojectapp.node.concrete.block;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class InitialBlockNode extends Node{

	private List<Node> innerNodes;

	public InitialBlockNode() {
		innerNodes = new ArrayList<Node>();
	}

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		for (Node innerNode : innerNodes){
			res.addAll(innerNode.getCodeParts());
			res.add(new CodePart(false, true, null, null));
		}

		res.add(new CodePart(false, false, null, new InitialBlockSetter(this)));// add more 

		return res;
	}
	
	@Override
	public ReturnObject run() {
		
		for (Node n : innerNodes){
			LevelManager.getInstance().getCodeRunningManager().takeSnapshot(n);
			n.run();
		}
		
		return new ReturnObject();	// TODO
	}

	class InitialBlockSetter extends Setter{

		public InitialBlockSetter(Node parent) {
			super("+" , false, parent, innerNodes.size());	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			toSet.setOrder(innerNodes.size());	
			toSet.setParent(getParent());
			innerNodes.add(toSet);
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Statement);

			return possibilities;
		}
	}

}
