package com.example.finalprojectapp.node;

import java.util.List;

import com.example.finalprojectapp.codewriting.codeline.CodePart;

public abstract class Node {

	private Node parent;
	private int order;
	
	private Scope scope;

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

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public abstract List<CodePart> getCodeParts();
	
	public abstract ReturnObject run();


}
