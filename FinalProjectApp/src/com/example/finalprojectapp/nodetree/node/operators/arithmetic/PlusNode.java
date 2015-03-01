package com.example.finalprojectapp.nodetree.node.operators.arithmetic;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.Node;

public class PlusNode extends Node{

	private Node left;
	private Node right;

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		if(left == null)
			res.add(new CodePart(false, false, null, new LeftSetter(this)));
		else
			res.addAll(left.getCodeParts());

		res.add(new CodePart(false, false, "+", null));

		if(right == null)
			res.add(new CodePart(false, false, null, new RightSetter(this)));
		else			
			res.addAll(right.getCodeParts());

		return res;
	}
	
	class LeftSetter extends Setter{

		final static int order = 0;

		public LeftSetter(Node parent) {
			super("< int expr >", true, parent, order);	// TODO	
		}

		@Override
		public void setChildNode(Node toSet) {
			left = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Int);

			return possibilities;
		}
	}
	
	class RightSetter extends Setter{

		final static int order = 0;

		public RightSetter(Node parent) {
			super("< int expr >", true, parent, order);	// TODO
			
		}

		@Override
		public void setChildNode(Node toSet) {
			right = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Int);

			return possibilities;
		}
	}

}
