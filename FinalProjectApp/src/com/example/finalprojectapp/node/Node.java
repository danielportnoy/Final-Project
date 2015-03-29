package com.example.finalprojectapp.node;

import java.util.List;

import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;

public abstract class Node {

	private Node parent;
	private int order;
	private Type type;
	
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

	public abstract List<CodeWritingPart> getCodeWritingParts();
	public abstract List<CodeRunningPart> getCodeRunningParts(Node target , boolean isHighlighted);

	
	public abstract ReturnObject run();


}
