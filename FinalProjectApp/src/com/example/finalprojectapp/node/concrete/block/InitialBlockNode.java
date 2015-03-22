package com.example.finalprojectapp.node.concrete.block;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
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
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		for (Node innerNode : innerNodes){
			res.addAll(innerNode.getCodeWritingParts());
			res.add(new CodeWritingPart(false, true, null, null));
		}

		res.add(new CodeWritingPart(false, false, null, new InitialBlockSetter(this)));// add more 

		return res;
	}
	
	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target,boolean isHighlighted) {
		
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		for (Node innerNode : innerNodes){
			res.addAll(innerNode.getCodeRunningParts(target,isHighlighted));
			res.add(new CodeRunningPart(false, true,false, null));
		}
		
		return res;
	}
	
	@Override
	public ReturnObject run() {
		
		for (Node n : innerNodes){
			n.run();
		}
		
		LevelManager.getInstance().takeSnapshot(this);	// TODO
		
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
