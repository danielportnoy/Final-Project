package com.example.finalprojectapp.node.concrete.block;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class BlockNode extends Node {

	private List<Node> innerNodes;

	public BlockNode() {
		innerNodes = new ArrayList<Node>();
		setType(Type.Statement);
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "{", null));
		res.add(new CodeWritingPart(false, true, null, null));

		for (Node innerNode : innerNodes){
			res.addAll(CodeWritingPart.tabber(innerNode.getCodeWritingParts()));
			res.add(new CodeWritingPart(false, true, null, null));
		}

		res.add(new CodeWritingPart(true, false, null, null));
		res.add(new CodeWritingPart(false, false, null, new BlockSetter(this)));	// add more 

		res.add(new CodeWritingPart(false, true, null, null));
		res.add(new CodeWritingPart(false, false, "}", null));

		return res;
	}
	
	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target,boolean isHighlighted) {
		
		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();
		
		res.add(new CodeRunningPart(false, false, isHighlighted, "{"));
		res.add(new CodeRunningPart(false, true, isHighlighted, null));

		for (Node innerNode : innerNodes){
			res.addAll(CodeRunningPart.tabber(innerNode.getCodeRunningParts(target,isHighlighted)));
			res.add(new CodeRunningPart(false, true,isHighlighted, null));
		}
		
		res.add(new CodeRunningPart(false, true, isHighlighted, null));
		res.add(new CodeRunningPart(false, false,isHighlighted, "}"));
		
		return res;
	}

	@Override
	public ReturnObject run() {
		
		for (Node n : innerNodes){
			n.run();
		}
		
		return new ReturnObject();	// TODO
	}
	
	class BlockSetter extends Setter{

		public BlockSetter(Node parent) {
			super("+",false,parent,innerNodes.size());	//TODO
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
