package com.example.finalprojectapp.node;

import java.util.List;


public abstract class Setter {

	private String text;
	private boolean mandatory;

	private Node parent;
	private int order;

	public abstract void setChildNode(Node toSet);

	public abstract List<Type> possibleTypes();

	public void addToBooleanScope(String identifier) {
		parent.getScope().getBooleanIdentifiers().put(identifier, order);
	}		

	public void addToIntegerScope(String identifier) {
		parent.getScope().getIntegerIdentifiers().put(identifier, order);
	}

	public Setter(String text, boolean mandatory,  Node parent , int order) {
		this.text = text;
		this.mandatory = mandatory;
		this.parent = parent;
		this.order = order;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public String getText() {
		return text;
	}

	public Node getParent() {
		return parent;
	}

	public int getOrder() {
		return order;
	}
}