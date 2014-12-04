package logics.grammar.literals;

import logics.grammar.expression.Literal_Expression;
import logics.visitor.CodeVisitor;

public class Integer_Literal extends Literal_Expression {

	private int value;
	
	public Integer_Literal() {
		value=0;
	}
	
	public Integer_Literal(int value) {
		setValue(value);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Literal_Expression accept(CodeVisitor v) {
		return v.visit(this);
	}

}
