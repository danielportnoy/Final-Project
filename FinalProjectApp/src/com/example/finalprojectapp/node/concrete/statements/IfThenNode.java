package com.example.finalprojectapp.node.concrete.statements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
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
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		if(childNode.equals(condition) && thenBody != null)
			used.addAll(thenBody.getUsedIdentifiers());
		else if(childNode.equals(thenBody))
			used = new HashSet<String>();

		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		if(intersection.isEmpty()){
			if(childNode.equals(condition))
				condition = null;
			else if(childNode.equals(thenBody))
				thenBody = null;
			return true;
		}
		else
			return false;
	}

	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(condition!=null)
			res.addAll(condition.getDeclaredIdentifiers());
		if(thenBody!=null)
			res.addAll(thenBody.getDeclaredIdentifiers());
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(condition!=null)
			res.addAll(condition.getUsedIdentifiers());
		if(thenBody!=null)
			res.addAll(thenBody.getUsedIdentifiers());
		return res;
	}


	@Override
	public Node getFirstNode() {
		if(condition != null)
			return condition;
		else if(thenBody != null)
			return thenBody;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "if (", null, this));

		if(condition == null)
			res.add(new CodeWritingPart(false, false, null, new ConditionSetter(this), this));
		else
			res.addAll(condition.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ")", null, this));

		if(thenBody == null){
			res.add(new CodeWritingPart(false, true, null, null, this));
			res.add(new CodeWritingPart(true, false, null, null, this));

			res.add(new CodeWritingPart(false, false, null, new ThenBodySetter(this), this));
		}
		else{
			if(thenBody instanceof BlockNode)	//TODO
				res.addAll(thenBody.getCodeWritingParts());
			else{
				res.add(new CodeWritingPart(false, true, null, null, this));
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
		res.addAll(condition.getCodeRunningParts(target, isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ")"));

		if(thenBody instanceof BlockNode)	//TODO
			res.addAll(thenBody.getCodeRunningParts(target, isHighlighted));
		else{
			res.add(new CodeRunningPart(false, true, isHighlighted, null));
			res.addAll(CodeRunningPart.tabber(thenBody.getCodeRunningParts(target, isHighlighted)));
		}

		return res;
	}

	@Override
	public ReturnObject run() throws MyException {

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
