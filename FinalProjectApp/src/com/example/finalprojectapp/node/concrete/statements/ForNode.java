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

/**
 * Holds the Logical data and functionality of a For 'Code part'.
 * @author daniel portnoy
 *
 */
public class ForNode extends Node{

	private Node init;
	private Node condition;
	private Node update;
	private Node body;

	public ForNode() {
		setType(Type.Statement);
	}

	@Override
	public boolean addChild(Node child, int order) {

		if(order > getChildNodes().size() - 1)
			return false;
		else{
			if(order == 0)
				init = child;
			else if(order == 1)
				condition = child;
			else if(order == 2)
				update = child;
			else if(order == 3)
				body = child;
		}
		return true;
	}

	@Override
	public List<Node> getChildNodes() {
		List<Node> res = new ArrayList<Node>();

		res.add(init);
		res.add(condition);
		res.add(update);
		res.add(body);

		return res;
	}

	@Override
	public boolean DeleteChildNode(Node childNode) {

		Set<String> used = new HashSet<String>();

		// Find any used identifiers after childNode.
		if(childNode.equals(init)){
			if(condition != null)
				used.addAll(condition.getUsedIdentifiers());
			if(update != null)
				used.addAll(update.getUsedIdentifiers());
			if(body != null)
				used.addAll(body.getUsedIdentifiers());
		}
		else if(childNode.equals(condition)){
			if(update != null)
				used.addAll(update.getUsedIdentifiers());
			if(body != null)
				used.addAll(body.getUsedIdentifiers());
		}
		else if(childNode.equals(update) && body != null)
			used.addAll(body.getUsedIdentifiers());
		else if(childNode.equals(body))
			used = new HashSet<String>();

		// Find any declared identifiers in childNode.
		Set<String> intersection = new HashSet<String>(used);
		intersection.retainAll(childNode.getDeclaredIdentifiers());

		// Check if deletion is valid.
		if(intersection.isEmpty()){	
			
			// delete the childNode.
			if(childNode.equals(init)){
				removeFromScope(init);
				init = null;
			}
			else if(childNode.equals(condition)){
				removeFromScope(condition);
				condition = null;
			}
			else if(childNode.equals(update)){
				removeFromScope(update);
				update = null;
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
		if(init!=null)
			res.addAll(init.getDeclaredIdentifiers());
		if(condition!=null)
			res.addAll(condition.getDeclaredIdentifiers());
		if(update!=null)
			res.addAll(update.getDeclaredIdentifiers());
		if(body!=null)
			res.addAll(body.getDeclaredIdentifiers());
		return res;
	}

	@Override
	public Set<String> getUsedIdentifiers() {

		HashSet<String> res = new HashSet<String>();
		if(init!=null)
			res.addAll(init.getUsedIdentifiers());
		if(condition!=null)
			res.addAll(condition.getUsedIdentifiers());
		if(update!=null)
			res.addAll(update.getUsedIdentifiers());
		if(body!=null)
			res.addAll(body.getUsedIdentifiers());
		return res;
	}

	@Override
	public Node getFirstNode() {
		if(init != null)
			return init;
		else if(condition != null)
			return condition;
		else if(update != null)
			return update;
		else if(body != null)
			return body;
		else
			return null;
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		// Add the for text.
		res.add(new CodeWritingPart(false, false, "for (", null, this));

		// Add the init Node or add '+' setter.
		if(init == null)
			res.add(new CodeWritingPart(false, false, null, new InitSetter(this), this));
		else
			res.addAll(init.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ";", null, this));

		// Add the condition Node or add '+' setter.
		if(condition == null)
			res.add(new CodeWritingPart(false, false, null, new ConditionSetter(this), this));
		else
			res.addAll(condition.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ";", null, this));

		// Add the update Node or add '+' setter.
		if(update == null)
			res.add(new CodeWritingPart(false, false, null, new UpdateSetter(this), this));
		else
			res.addAll(update.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ")", null, this));

		// Add the body Node or add '+' setter.
		if(body == null){
			res.add(new CodeWritingPart(false, true, null, null, this));
			res.add(new CodeWritingPart(true, false, null, null, this));

			res.add(new CodeWritingPart(false, false, null, new BodySetter(this), this));
		}
		else{
			if(body instanceof BlockNode)
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

		// Add the for text.
		res.add(new CodeRunningPart(false, false,isHighlighted, "for ("));

		// Add the init Node.
		if(init != null)
			res.addAll(init.getCodeRunningParts(target, isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		// Add the condition Node.
		if(condition != null)
			res.addAll(condition.getCodeRunningParts(target, isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		// Add the update Node.
		if(update != null)
			res.addAll(update.getCodeRunningParts(target, isHighlighted));	
		res.add(new CodeRunningPart(false, false,isHighlighted, ")"));

		// Add the body Node.
		if(body instanceof BlockNode)
			res.addAll(body.getCodeRunningParts(target, isHighlighted));
		else{
			res.add(new CodeRunningPart(false, true,isHighlighted, null));
			res.addAll(CodeRunningPart.tabber(body.getCodeRunningParts(target, isHighlighted)));
		}

		return res;
	}

	@Override
	public ReturnObject run() throws MyException {

		LevelManager.getInstance().takeSnapshot(this);

		if(init != null)
			init.run();

		if(condition!=null){

			while(condition.run().getBoolValue()){
				body.run();

				if(update != null)
					update.run();
			}
		}

		return new ReturnObject();
	}

	/**
	 * Logical and Graphic data and functionality of the Init button for a For 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class InitSetter extends Setter{

		static final int ORDER = 0;

		public InitSetter(Node parent) {
			super(false, parent ,ORDER);
		}

		@Override
		public void setChildNode(Node toSet) {
			init = toSet;
			toSet.setOrder(ORDER);
			toSet.setParent(getParent());

			toSet.setHideSemicolon(true);
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.ForInit);

			return possibilities;
		}

	}

	/**
	 * Logical and Graphic data and functionality of the Condition button for a For 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class ConditionSetter extends Setter{

		final static int ORDER = 1;

		public ConditionSetter(Node parent) {
			super(false, parent, ORDER);
		}

		@Override
		public void setChildNode(Node toSet) {
			condition = toSet;
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

	/**
	 * Logical and Graphic data and functionality of the Update button for a For 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class UpdateSetter extends Setter{

		static final int ORDER = 2;

		public UpdateSetter(Node parent) {
			super(false, parent ,ORDER);
		}

		@Override
		public void setChildNode(Node toSet) {
			update = toSet;
			toSet.setOrder(ORDER);
			toSet.setParent(getParent());

			toSet.setHideSemicolon(true);
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.ForUpdate);

			return possibilities;
		}

	}

	/**
	 * Logical and Graphic data and functionality of the Body button for a For 'Code part'.
	 * @author daniel portnoy
	 *
	 */
	class BodySetter extends Setter{

		static final int ORDER = 4;

		public BodySetter(Node parent) {
			super(true, parent ,ORDER);
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
