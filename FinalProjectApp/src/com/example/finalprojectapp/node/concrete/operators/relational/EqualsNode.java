package com.example.finalprojectapp.node.concrete.operators.relational;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class EqualsNode extends Node {

	private Node left;
	private Type left_type;

	private Node right;
	private Type right_type;
	
	public EqualsNode() {
		setType(Type.Bool);
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		if(left == null)
			res.add(new CodeWritingPart(false, false, null, new LeftSetter(this)));
		else			
			res.addAll(left.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, "==", null));

		if(right == null)
			res.add(new CodeWritingPart(false, false, null, new RightSetter(this)));
		else			
			res.addAll(right.getCodeWritingParts());

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target,boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.addAll(left.getCodeRunningParts(target,isHighlighted));

		res.add(new CodeRunningPart(false, false,isHighlighted, "=="));

		res.addAll(right.getCodeRunningParts(target,isHighlighted));

		return res;
	}

	@Override
	public ReturnObject run() {

		LevelManager.getInstance().takeSnapshot(this);

		if(left_type == Type.Bool && right_type == Type.Bool)
			return new ReturnObject(left.run().getBoolValue() == right.run().getBoolValue());
		else if(left_type == Type.Int && right_type == Type.Int)
			return new ReturnObject(left.run().getIntValue() == right.run().getIntValue());
		
		return new ReturnObject();	// TODO
	}

	class LeftSetter extends Setter{

		final static int order = 0;

		public LeftSetter(Node parent) {	// TODO	
			super("< expr >", true, parent, order);	
		}

		@Override
		public void setChildNode(Node toSet) {
			left = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
			
			left_type = toSet.getType();
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();

			if(right_type == null){
				possibilities.add(Type.Bool);
				possibilities.add(Type.Int);
			}
			else
				possibilities.add(right_type);

			return possibilities;
		}
	}
	
	class RightSetter extends Setter{

		final static int order = 0;

		public RightSetter(Node parent) {	// TODO	
			super("< expr >", true, parent, order);	
		}

		@Override
		public void setChildNode(Node toSet) {
			right = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
			
			right_type = toSet.getType();
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();

			if(left_type == null){
				possibilities.add(Type.Bool);
				possibilities.add(Type.Int);
			}
			else
				possibilities.add(left_type);

			return possibilities;
		}
	}

}
