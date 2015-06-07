package com.example.finalprojectapp.node.concrete.vardec;

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

public class IntVarDecNode extends Node {

	private String identifier;
	private Node initialValue;

	public IntVarDecNode(String identifier) {
		this.identifier = identifier;
		setType(Type.Statement);
	}

	@Override
	public List<Node> getChildNodes() {
		List<Node> res = new ArrayList<Node>();

		res.add(initialValue);

		return res;
	}


	@Override
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		if(childNode.equals(initialValue))
			used = new HashSet<String>();

		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		if(intersection.isEmpty()){
			if(childNode.equals(initialValue)){
				removeFromScope(initialValue);
			}
			initialValue = null;

			return true;
		}
		else
			return false;
	}

	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(initialValue!=null)
			res.addAll(initialValue.getDeclaredIdentifiers());
		res.add(identifier);
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(initialValue!=null)
			res.addAll(initialValue.getUsedIdentifiers());
		return res;
	}

	@Override
	public Node getFirstNode() {
		if(initialValue != null)
			return initialValue;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "int "+ identifier, null, this));

		if(initialValue == null){
			res.add(new CodeWritingPart(false, false, null, new InitialValueSetter(this), this));

			if(!isHideSemicolon())
				res.add(new CodeWritingPart(false, false, ";", null, this));
		}
		else{
			res.add(new CodeWritingPart(false, false, "=", null, this));
			res.addAll(initialValue.getCodeWritingParts());

			if(!isHideSemicolon())
				res.add(new CodeWritingPart(false, false, ";", null, this));
		}

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, "int "+ identifier));

		if(initialValue != null){
			res.add(new CodeRunningPart(false, false,isHighlighted, "="));
			res.addAll(initialValue.getCodeRunningParts(target, isHighlighted));
		}

		if(!isHideSemicolon())
			res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		return res;
	}


	@Override
	public ReturnObject run() throws MyException {

		LevelManager.getInstance().takeSnapshot(this);

		if(initialValue == null)
			LevelManager.getInstance().putIntegerValueToIdentifier(identifier, 0);
		else
			LevelManager.getInstance().putIntegerValueToIdentifier(identifier, initialValue.run().getIntValue());

		return new ReturnObject();
	}


	class InitialValueSetter extends Setter{

		final static int order = 0;

		public InitialValueSetter(Node parent) {
			super(/*"< = int expr >", */false, parent, order);
		}

		@Override
		public void setChildNode(Node toSet) {
			initialValue = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());

			toSet.setHideSemicolon(true);
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Int);

			return possibilities;
		}

	}
}
