package com.example.finalprojectapp.node.concrete.operators.assignment;

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

public class SimpleAssignmentNode extends Node {

	private Type typeOfIdentifier;
	private String identifierName;

	private Node assignmentValue;

	public SimpleAssignmentNode(Type typeOfIdentifier, String identifierName) {
		this.typeOfIdentifier = typeOfIdentifier;
		this.identifierName = identifierName;
		setType(Type.Statement);
	}

	@Override
	public boolean addChild(Node child, int order) {

		if(order > getChildNodes().size() - 1)
			return false;
		else{
			assignmentValue = child;
		}
		return true;
	}

	@Override
	public List<Node> getChildNodes() {
		return null;
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		if(childNode.equals(assignmentValue))
			used = new HashSet<String>();

		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		if(intersection.isEmpty()){
			if(childNode.equals(assignmentValue))
				assignmentValue = null;
			return true;
		}
		else
			return false;
	}


	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(assignmentValue != null)
			res.addAll(assignmentValue.getDeclaredIdentifiers());
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(assignmentValue != null)
			res.addAll(assignmentValue.getUsedIdentifiers());
		res.add(identifierName);
		return res;
	}

	@Override
	public Node getFirstNode() {
		if(assignmentValue != null)
			return assignmentValue;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, identifierName , null, this));
		res.add(new CodeWritingPart(false, false, "=", null, this));


		if(assignmentValue == null)
			res.add(new CodeWritingPart(false, false, null, new AssignmentValueSetter(this), this));
		else			
			res.addAll(assignmentValue.getCodeWritingParts());

		if(!isHideSemicolon())
			res.add(new CodeWritingPart(false, false, ";", null, this));

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target,boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, identifierName));
		res.add(new CodeRunningPart(false, false,isHighlighted, "="));

		res.addAll(assignmentValue.getCodeRunningParts(target, isHighlighted));

		if(!isHideSemicolon())
			res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		return res;
	}

	@Override
	public ReturnObject run() throws MyException {

		LevelManager.getInstance().takeSnapshot(this);

		switch (typeOfIdentifier) {
		case Bool:
			LevelManager.getInstance().putBooleanValueToIdentifier(identifierName, assignmentValue.run().getBoolValue());
			break;
		case Int:
			LevelManager.getInstance().putIntegerValueToIdentifier(identifierName, assignmentValue.run().getIntValue());
			break;
		default:
			break;
		}

		return new ReturnObject();
	}

	class AssignmentValueSetter extends Setter{

		final static int order = 0;

		public AssignmentValueSetter(Node parent) {	// TODO	
			super(/*null, */true, parent, order);	

			// TODO	
			/*if(typeOfIdentifier.equals(Type.Bool))
				setText("< bool expr >");
			else if(typeOfIdentifier.equals(Type.Int))
				setText("< int expr >");*/

		}

		@Override
		public void setChildNode(Node toSet) {
			assignmentValue = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());

			toSet.setHideSemicolon(true);
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(typeOfIdentifier);

			return possibilities;
		}
	}

}
