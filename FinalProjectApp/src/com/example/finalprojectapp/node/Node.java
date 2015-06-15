package com.example.finalprojectapp.node;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;

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

	public abstract List<CodeWritingPart> getCodeWritingParts();
	public abstract List<CodeRunningPart> getCodeRunningParts(Node target , boolean isHighlighted);

	public abstract ReturnObject run() throws MyException;

	public abstract Node getFirstNode();

	public abstract Set<String> getDeclaredIdentifiers();

	public abstract Set<String> getUsedIdentifiers();

	public abstract boolean DeleteChildNode(Node childNode);

	public abstract List<Node> getChildNodes();

	public abstract boolean addChild(Node child, int order);

	public void removeFromScope(Node n){

		for (int i = n.getOrder() + 1 ; i < n.getParent().getChildNodes().size(); i++)
			if(n.getParent().getChildNodes().get(i) != null)
				n.getParent().getChildNodes().get(i).setOrder(i-1);

		getScope().removeIdentifier(n.getOrder());
		reOrderScope(n.getOrder(), -1);

	}

	public void reOrderScope(int from, int val){
		for (Entry<String, Integer> entry : getScope().getIntegerIdentifiers().entrySet()) {			if(entry.getValue() >= from)				getScope().getIntegerIdentifiers().put(entry.getKey(), entry.getValue() + val);		}
		for (Entry<String, Integer> entry : getScope().getBooleanIdentifiers().entrySet()) {			if(entry.getValue() >= from)				getScope().getBooleanIdentifiers().put(entry.getKey(), entry.getValue() + val);		}
	}
}
