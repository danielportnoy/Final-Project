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

public class WhileNode extends Node{

	private Node condition;
	private Node body;

	public WhileNode() {
		setType(Type.Statement);
	}

	@Override
	public boolean addChild(Node child, int order) {

		if(order > getChildNodes().size() - 1)
			return false;
		else{
			if(order == 0)
				condition = child;
			else if(order == 1)
				body = child;
		}
		return true;
	}
	
	@Override
	public List<Node> getChildNodes() {
		List<Node> res = new ArrayList<Node>();

		res.add(condition);
		res.add(body);

		return res;
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		if(childNode.equals(condition) && body != null)
			used.addAll(body.getUsedIdentifiers());
		else if(childNode.equals(body))
			used = new HashSet<String>();

		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		if(intersection.isEmpty()){
			if(childNode.equals(condition)){
				removeFromScope(condition);
				condition = null;
			}
			else if(childNode.equals(body)){
				removeFromScope(body);
				body = null;
			}
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
		if(body!=null)
			res.addAll(body.getDeclaredIdentifiers());
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(condition!=null)
			res.addAll(condition.getUsedIdentifiers());
		if(body!=null)
			res.addAll(body.getUsedIdentifiers());
		return res;
	}


	@Override
	public Node getFirstNode() {
		if(condition != null)
			return condition;
		else if(body != null)
			return body;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "while (", null, this));

		if(condition == null)
			res.add(new CodeWritingPart(false, false, null, new ConditionSetter(this), this));
		else
			res.addAll(condition.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ")", null, this));

		if(body == null){
			res.add(new CodeWritingPart(false, true, null, null, this));
			res.add(new CodeWritingPart(true, false, null, null, this));

			res.add(new CodeWritingPart(false, false, null, new ThenBodySetter(this), this));
		}
		else{
			if(body instanceof BlockNode)	//TODO
				res.addAll(body.getCodeWritingParts());
			else{
				res.add(new CodeWritingPart(false, true, null, null, this));
				res.addAll(CodeWritingPart.tabber(body.getCodeWritingParts()));
			}

		}

		return res;

	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, "while ("));
		res.addAll(condition.getCodeRunningParts(target, isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ")"));

		if(body instanceof BlockNode)	//TODO
			res.addAll(body.getCodeRunningParts(target, isHighlighted));
		else{
			res.add(new CodeRunningPart(false, true, isHighlighted, null));
			res.addAll(CodeRunningPart.tabber(body.getCodeRunningParts(target, isHighlighted)));
		}

		return res;
	}

	@Override
	public ReturnObject run() throws MyException {

		LevelManager.getInstance().takeSnapshot(this);

		while(condition.run().getBoolValue())
			body.run();

		return new ReturnObject();
	}

	class ConditionSetter extends Setter{

		final static int ORDER = 0;

		public ConditionSetter(Node parent) {
			super(/*"< bool expr >", */true, parent, ORDER);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			condition = toSet;
			toSet.setOrder(ORDER);
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

		static final int ORDER = 1;

		public ThenBodySetter(Node parent) {
			super(/*"+", */true, parent ,ORDER);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			body = toSet;
			toSet.setOrder(ORDER);
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
