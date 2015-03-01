package com.example.finalprojectapp.nodetree.node;

import java.util.List;

import com.example.finalprojectapp.codescreenlogic.Scope;
import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;

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

}
