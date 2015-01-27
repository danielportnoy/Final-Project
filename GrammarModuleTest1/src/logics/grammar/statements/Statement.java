package logics.grammar.statements;

import logics.visitor.CodeVisitable;

public abstract class Statement implements CodeVisitable<Void> {

	public String name;

	public Statement(String name) {
		this.name = name;
	}

}
