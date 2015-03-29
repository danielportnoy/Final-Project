package com.example.finalprojectapp.node.concrete.statements;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.block.BlockNode;

public class IfThenNode extends Node{

	private Node condition;
	private Node thenBody;
	
	public IfThenNode() {
		setType(Type.Statement);
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "if (", null));

		if(condition == null)
			res.add(new CodeWritingPart(false, false, null, new ConditionSetter(this)));
		else
			res.addAll(condition.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ")", null));

		if(thenBody == null){
			res.add(new CodeWritingPart(false, true, null, null));
			res.add(new CodeWritingPart(true, false, null, null));

			res.add(new CodeWritingPart(false, false, null, new ThenBodySetter(this)));
		}
		else{
			if(thenBody instanceof BlockNode)	//TODO
				res.addAll(thenBody.getCodeWritingParts());
			else{
				res.add(new CodeWritingPart(false, true, null, null));
				res.addAll(CodeWritingPart.tabber(thenBody.getCodeWritingParts()));
			}

		}

		return res;

	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {
		
		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, "if ("));
		res.addAll(condition.getCodeRunningParts(target,isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ")"));

		if(thenBody instanceof BlockNode)	//TODO
			res.addAll(thenBody.getCodeRunningParts(target,isHighlighted));
		else{
			res.add(new CodeRunningPart(false, true, isHighlighted, null));
			res.addAll(CodeRunningPart.tabber(thenBody.getCodeRunningParts(target,isHighlighted)));
		}

		return res;
	}

	@Override
	public ReturnObject run() {

		LevelManager.getInstance().takeSnapshot(this);

		if(condition.run().getBoolValue())
			thenBody.run();
		
		return new ReturnObject();
	}

	class ConditionSetter extends Setter{

		final static int order = 0;

		public ConditionSetter(Node parent) {
			super("< bool expr >", true, parent, order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			condition = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Bool);

			return possibilities;
		}
	}

	class ThenBodySetter extends Setter{

		static final int order = 1;

		public ThenBodySetter(Node parent) {
			super("+", true, parent ,order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			thenBody = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Statement);

			return possibilities;
		}

	}

}
