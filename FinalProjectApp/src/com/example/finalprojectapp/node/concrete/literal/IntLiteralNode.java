package com.example.finalprojectapp.node.concrete.literal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Type;

/**
 * Holds the Logical data and functionality of a Integer Literal 'Code part'.
 * @author daniel portnoy
 *
 */
public class IntLiteralNode extends Node {

	private int value;

	public IntLiteralNode(int value) {
		this.value = value;
		setType(Type.Int);
	}
	
	@Override
	public boolean addChild(Node child, int order) {
		return false;
	}
	
	@Override
	public List<Node> getChildNodes() {
		return null;
	}
	
	@Override
	public boolean DeleteChildNode(Node childNode) {
		return true;
	}
	
	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {
		
		HashSet<String> res = new HashSet<String>();
		return res;
	}
	
	@Override
	public Node getFirstNode() {
		return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		// Add the identifier name.
		res.add(new CodeWritingPart(false, false, value + "", null, this));

		return res;
	}
	
	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {
		
		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		// Add the identifier name.
		res.add(new CodeRunningPart(false, false,isHighlighted, value + ""));

		return res;
	}

	
	@Override
	public ReturnObject run() throws MyException {
		return new ReturnObject(value);
	}

}
