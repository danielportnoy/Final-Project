package com.example.finalprojectapp.nodetree.node.block;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.Node;

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
