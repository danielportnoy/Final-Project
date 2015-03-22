package com.example.finalprojectapp.node.concrete.operators.arithmetic;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class PlusNode extends Node{

	private Node left;
	private Node right;

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		if(left == null)
			res.add(new CodeWritingPart(false, false, null, new LeftSetter(this)));
		else
			res.addAll(left.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, "+", null));

		if(right == null)
			res.add(new CodeWritingPart(false, false, null, new RightSetter(this)));
		else			
			res.addAll(right.getCodeWritingParts());

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {
		
		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.addAll(left.getCodeRunningParts(target,isHighlighted));

		res.add(new CodeRunningPart(false, false,isHighlighted, "+"));

		res.addAll(right.getCodeRunningParts(target,isHighlighted));

		return res;
	}


	@Override
	public ReturnObject run() {
		LevelManager.getInstance().takeSnapshot(this);
		return new ReturnObject(left.run().getIntValue() + right.run().getIntValue());
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
