package com.example.finalprojectapp.node.concrete.operators.arithmetic;

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
 * Holds the Logical data and functionality of a Multiplication 'Code part'.
 * @author daniel portnoy
 *
 */
public class MultiplicationNode extends Node{

	private Node left;
	private Node right;

	public MultiplicationNode() {
		setType(Type.Int);
	}

	@Override
	public boolean addChild(Node child, int order) {

		if(order > getChildNodes().size() - 1)
			return false;
		else{
			if(order == 0)
				left = child;
			else if(order == 1)
				right = child;
		}
		return true;
	}

	@Override
	public List<Node> getChildNodes() {
		List<Node> res = new ArrayList<Node>();

		res.add(left);
		res.add(right);

		return res;
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		// Find any used identifiers after childNode.
		if(childNode.equals(left) && right != null)
			used.addAll(right.getUsedIdentifiers());
		else if(childNode.equals(right))
			used = new HashSet<String>();

		// Find any declared identifiers in childNode.
		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		// Check if deletion is valid.
		if(intersection.isEmpty()){

			// delete the childNode.
			if(childNode.equals(left)){
				removeFromScope(left);
				left = null;
			}
			else if(childNode.equals(right)){
				removeFromScope(right);
				right = null;
			}
			return true;
		}
		else
			return false;
	}

	@Override
	public Set<String> getDeclaredIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(left!=null)
			res.addAll(left.getDeclaredIdentifiers());
		if(right!=null)
			res.addAll(right.getDeclaredIdentifiers());
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(left!=null)
			res.addAll(left.getUsedIdentifiers());
		if(right!=null)
			res.addAll(right.getUsedIdentifiers());
		return res;
	}

	@Override
	public Node getFirstNode() {
		if(left != null)
			return left;
		else if(right != null)
			return right;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		// Add the left Node or add '+' setter.
		if(left == null)
			res.add(new CodeWritingPart(false, false, null, new LeftSetter(this), this));
		else
			res.addAll(left.getCodeWritingParts());

		// Add the Multiplication sign (*).
		res.add(new CodeWritingPart(false, false, Constants.MULTIPLICATION_CODE_TEXT, null, this));

		// Add the right Node or add '+' setter.
		if(right == null)
			res.add(new CodeWritingPart(false, false, null, new RightSetter(this), this));
		else			
			res.addAll(right.getCodeWritingParts());

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		// Add the left Node.
		res.addAll(left.getCodeRunningParts(target, isHighlighted));

		// Add the Multiplication sign (*).
		res.add(new CodeRunningPart(false, false,isHighlighted, Constants.MULTIPLICATION_CODE_TEXT));

		// Add the right Node.
		res.addAll(right.getCodeRunningParts(target, isHighlighted));

		return res;
	}


	@Override
	public ReturnObject run() throws MyException {
		LevelManager.getInstance().takeSnapshot(this);
		return new ReturnObject(left.run().getIntValue() * right.run().getIntValue());
	}

	/**
	 * Logical and Graphic data and functionality of the left button for a Multiplication 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class LeftSetter extends Setter{

		final static int ORDER = 0;

		public LeftSetter(Node parent) {
			super(true, parent, ORDER);
		}

		@Override
		public void setChildNode(Node toSet) {
			left = toSet;
			toSet.setOrder(ORDER);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Int);

			return possibilities;
		}
	}

	/**
	 * Logical and Graphic data and functionality of the right button for a Multiplication 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class RightSetter extends Setter{

		final static int ORDER = 1;

		public RightSetter(Node parent) {
			super(true, parent, ORDER);

		}

		@Override
		public void setChildNode(Node toSet) {
			right = toSet;
			toSet.setOrder(ORDER);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {
			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Int);

			return possibilities;
		}
	}
}
