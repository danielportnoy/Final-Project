package com.example.finalprojectapp.node.concrete.operators.logical;

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

public class NotNode extends Node {

	private Node innerNode;

	public NotNode() {
		setType(Type.Bool);
	}

	@Override
	public boolean addChild(Node child, int order) {
		
		if(order > getChildNodes().size() - 1)
			return false;
		else{
			if(order == 0)
				innerNode = child;
		}
		return true;
	}

	@Override
	public List<Node> getChildNodes() {
		List<Node> res = new ArrayList<Node>();

		res.add(innerNode);

		return res;
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		if(childNode.equals(innerNode))
			used = new HashSet<String>();

		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		if(intersection.isEmpty()){
			if(childNode.equals(innerNode)){
				removeFromScope(innerNode);
				innerNode = null;
			}
			return true;
		}
		else
			return false;
	}

	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(innerNode!=null)
			res.addAll(innerNode.getDeclaredIdentifiers());
		
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(innerNode!=null)
			res.addAll(innerNode.getUsedIdentifiers());
	
		return res;
	}

	@Override
	public Node getFirstNode() {
		if(innerNode != null)
			return innerNode;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();
		
		res.add(new CodeWritingPart(false, false, "! ", null, this));
		
		if(innerNode == null)
			res.add(new CodeWritingPart(false, false, null, new InnerNodeSetter(this), this));
		else
			res.addAll(innerNode.getCodeWritingParts());

		if(!isHideSemicolon())
			res.add(new CodeWritingPart(false, false, ";", null, this));

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, "! "));
		
		res.addAll(innerNode.getCodeRunningParts(target, isHighlighted));

		if(!isHideSemicolon())
			res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		return res;
	}

	@Override
	public ReturnObject run() throws MyException {
		LevelManager.getInstance().takeSnapshot(this);
		return new ReturnObject(!innerNode.run().getBoolValue());
	}

	class InnerNodeSetter extends Setter{

		final static int ORDER = 0;

		public InnerNodeSetter(Node parent) {
			super(/*"< bool expr >", */true, parent, ORDER);	// TODO	
		}

		@Override
		public void setChildNode(Node toSet) {
			innerNode = toSet;
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

}
