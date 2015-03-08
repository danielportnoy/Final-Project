package com.example.finalprojectapp.node.concrete.block;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class BlockNode extends Node {

	private List<Node> innerNodes;

	public BlockNode() {
		innerNodes = new ArrayList<Node>();
	}

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, "{", null));
		res.add(new CodePart(false, true, null, null));

		for (Node innerNode : innerNodes){
			res.addAll(CodePart.tabber(innerNode.getCodeParts()));
			res.add(new CodePart(false, true, null, null));
		}

		res.add(new CodePart(true, false, null, null));
		res.add(new CodePart(false, false, null, new BlockSetter(this)));	// add more 

		res.add(new CodePart(false, true, null, null));
		res.add(new CodePart(false, false, "}", null));

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
	
	class BlockSetter extends Setter{

		public BlockSetter(Node parent) {
			super("+",false,parent,innerNodes.size());	//TODO
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
