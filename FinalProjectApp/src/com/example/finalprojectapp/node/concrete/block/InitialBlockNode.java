package com.example.finalprojectapp.node.concrete.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class InitialBlockNode extends Node{

	private List<Node> innerNodes;

	public InitialBlockNode() {
		innerNodes = new ArrayList<Node>();
		setType(Type.Statement);
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {
		int startingPosition = 0;

		while(!childNode.equals(innerNodes.get(startingPosition)))
			startingPosition++;

		Set<String> used = new HashSet<String>();

		for (int i = startingPosition + 1; i < innerNodes.size() ; i++)
			used.addAll(innerNodes.get(i).getUsedIdentifiers());

		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		if(intersection.isEmpty()){
			innerNodes.remove(startingPosition);
			return true;
		}
		else
			return false;
	}

	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();

		for (Node n : innerNodes)
			res.addAll(n.getDeclaredIdentifiers());

		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();

		for (Node n : innerNodes)
			res.addAll(n.getUsedIdentifiers());

		return res;
	}

	@Override
	public Node getFirstNode() {
		if(innerNodes.get(0) != null)
			return innerNodes.get(0);
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		for (Node innerNode : innerNodes){
			res.addAll(innerNode.getCodeWritingParts());
			res.add(new CodeWritingPart(false, true, null, null, this));
		}

		res.add(new CodeWritingPart(false, false, null, new InitialBlockSetter(this), this));// add more 

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target,boolean isHighlighted) {

		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		for (Node innerNode : innerNodes){
			res.addAll(innerNode.getCodeRunningParts(target,isHighlighted));
			res.add(new CodeRunningPart(false, true,false, null));
		}

		return res;
	}

	@Override
	public ReturnObject run() {

		for (Node n : innerNodes){
			n.run();
		}

		LevelManager.getInstance().takeSnapshot(this);	// TODO

		return new ReturnObject();	// TODO
	}

	class InitialBlockSetter extends Setter{

		public InitialBlockSetter(Node parent) {
			super("+" , false, parent, innerNodes.size());	//TODO
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
