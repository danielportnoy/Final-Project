package logics.grammar;

import logics.grammar.expression.Expression;
import logics.visitor.CodeVisitor;

public class Identifier extends Expression {
	
	private String name;
	
	public Identifier(String name) {
		this.name = name;
	}

	@Override
	public String accept(CodeVisitor v) {
		return v.visit(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
