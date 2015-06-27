package com.example.finalprojectapp.node;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;

/**
 * Logical data and functionality of a 'Code part'.
 * @author daniel portnoy
 *
 */
public abstract class Node {

	private Node parent;
	private int order;
	private Type type;

	private Scope scope;

	private boolean hideSemicolon = false;

	private boolean isErasable = true;

	public Node() {
		scope = new Scope();
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int oredr) {
		this.order = oredr;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public boolean isHideSemicolon() {
		if(hideSemicolon)
			return true;

		Node parent = getParent();
		while (parent != null) {
			if(parent.isHideSemicolon())
				return true;
			parent = parent.getParent();
		}

		return false;
	}

	public void setHideSemicolon(boolean hideSemicolon) {
		this.hideSemicolon = hideSemicolon;
	}

	public boolean isErasable() {
		return isErasable;
	}

	public void setErasable(boolean isErasable) {
		this.isErasable = isErasable;
	}

	/**
	 * Get a Visual data representation of the Logical code in the Writing mode.
	 * @return List of CodeWritingParts.
	 */
	public abstract List<CodeWritingPart> getCodeWritingParts();
	
	/**
	 * Get a Visual data representation of the Logical code in the Running mode.
	 * @return List of CodeRunningParts.
	 */
	public abstract List<CodeRunningPart> getCodeRunningParts(Node target , boolean isHighlighted);

	/**
	 * Simulates the running of the code.
	 * @return ReturnObject
	 * @throws MyException
	 */
	public abstract ReturnObject run() throws MyException;

	public abstract Node getFirstNode();

	/**
	 * Get the declared identifiers in the Node.
	 * @return Set of the identifiers names.
	 */
	public abstract Set<String> getDeclaredIdentifiers();

	/**
	 * Get the used identifiers in the Node.
	 * @return Set of the identifiers names.
	 */
	public abstract Set<String> getUsedIdentifiers();

	/**
	 * Delete a child Node of this Node.
	 * @param childNode - the Node to be deleted.
	 * @return success or failure of the delete.
	 */
	public abstract boolean DeleteChildNode(Node childNode);

	/**
	 * Get the Child Nodes of this Node.
	 * @return A list of Nodes.
	 */
	public abstract List<Node> getChildNodes();

	/**
	 * Adds a child Node in the specified location in this Node.
	 * @param child
	 * @param order
	 * @return success or failure of the addition.
	 */
	public abstract boolean addChild(Node child, int order);

	/**
	 * Removes the Node n from its parent scope.
	 * @param n - Node.
	 */
	public void removeFromScope(Node n){

		for (int i = n.getOrder() + 1 ; i < n.getParent().getChildNodes().size(); i++){
			
			// set node order to be (own order -1)
			if(n.getParent().getChildNodes().get(i) != null)
				n.getParent().getChildNodes().get(i).setOrder(i-1);
		}

		// remove identifiers.
		getScope().removeIdentifier(n.getOrder());
		
		// reorder scope.
		reOrderScope(n.getOrder(), -1);

	}

	/**
	 * Reorders the scope of this Node scope from given position, by a given value.
	 * @param from
	 * @param val
	 */
	public void reOrderScope(int from, int val){
		
		// reorder integer identifiers.
		for (Entry<String, Integer> entry : getScope().getIntegerIdentifiers().entrySet()) {			if(entry.getValue() >= from)				getScope().getIntegerIdentifiers().put(entry.getKey(), entry.getValue() + val);		}
		
		// reorder boolean identifiers.
		for (Entry<String, Integer> entry : getScope().getBooleanIdentifiers().entrySet()) {			if(entry.getValue() >= from)				getScope().getBooleanIdentifiers().put(entry.getKey(), entry.getValue() + val);		}
	}
}
