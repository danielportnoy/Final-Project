package com.example.finalprojectapp.node;

import java.util.List;

/**
 * Logical and Graphic data and functionality of a button for code writing.
 * @author daniel portnoy
 *
 */
public abstract class Setter {

	private boolean mandatory;

	private Node parent;
	private int order;

	/**
	 * Set the parent Node the child Node.
	 * @param toSet - child Node.
	 */
	public abstract void setChildNode(Node toSet);

	/**
	 * 
	 * @return List of all possible types. 
	 */
	public abstract List<Type> possibleTypes();

	/**
	 * Adds the boolean identifier to the Scope.
	 * @param identifier
	 */
	public void addToBooleanScope(String identifier) {
		parent.getScope().getBooleanIdentifiers().put(identifier, order);
	}		

	/**
	 * Adds the integer identifier to the Scope.
	 * @param identifier
	 */
	public void addToIntegerScope(String identifier) {
		parent.getScope().getIntegerIdentifiers().put(identifier, order);
	}

	/**
	 * 
	 * @param mandatory
	 * @param parent
	 * @param order
	 */
	public Setter(boolean mandatory,  Node parent , int order) {
		this.mandatory = mandatory;
		this.parent = parent;
		this.order = order;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public Node getParent() {
		return parent;
	}

	public int getOrder() {
		return order;
	}
}