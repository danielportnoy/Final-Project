package com.example.finalprojectapp.node.concrete.vardec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

/**
 * Holds the Logical data and functionality of a Boolean Variable Deceleration Node 'Code part'.
 * @author daniel portnoy
 *
 */
public class BoolVarDecNode extends Node {

	private String identifier;
	private Node initialValue;

	public BoolVarDecNode(String identifier) {
		this.identifier = identifier;
		setType(Type.Statement);
	}

	@Override
	public boolean addChild(Node child, int order) {

		if(order > getChildNodes().size() - 1)
			return false;
		else
			initialValue = child;

		return true;
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
		
		// Find any used identifiers after childNode.
		if(childNode.equals(initialValue))
			used = new HashSet<String>();

		// Find any declared identifiers in childNode.
		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		// Check if deletion is valid.
		if(intersection.isEmpty()){
			
			// delete the childNode.
			if(childNode.equals(initialValue)){
				removeFromScope(initialValue);
				initialValue = null;
			}
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

		// Add the identifier name and Type.
		res.add(new CodeWritingPart(false, false, Constants.BOOLEAN_CODE_TEXT + " " + identifier, null, this));

		// Add the initial Value Node or add '+' setter.
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

		// Add the identifier name and Type.
		res.add(new CodeRunningPart(false, false,isHighlighted, Constants.BOOLEAN_CODE_TEXT + " " + identifier));

		// Add the initial Value Node.
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
			LevelManager.getInstance().putBooleanValueToIdentifier(identifier, false);
		else
			LevelManager.getInstance().putBooleanValueToIdentifier(identifier, initialValue.run().getBoolValue());

		return new ReturnObject();
	}

	/**
	 * Logical and Graphic data and functionality of the Initial Value button for a BoolVarDec 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class InitialValueSetter extends Setter{

		static final int ORDER = 0;

		public InitialValueSetter(Node parent) {
			super(false ,parent , ORDER);
		}

		@Override
		public void setChildNode(Node toSet) {
			initialValue = toSet;
			toSet.setOrder(ORDER);
			toSet.setParent(getParent());

			toSet.setHideSemicolon(true);
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Bool);

			return possibilities;
		}

	}
}
