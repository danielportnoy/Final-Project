package com.example.finalprojectapp.nodetree.node.block;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.Node;

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
