package logics.grammar.expression;

import logics.grammar.operators.Boolean_Binary_Operator;
import logics.visitor.CodeVisitor;

public class Boolean_Binary_Expression extends Binary_Expression{

	public Boolean_Binary_Expression(Expression left, Expression right,Boolean_Binary_Operator op) {
		super(left, right, op);	
	}

	@Override
	public Boolean accept(CodeVisitor v) {
		return v.visit(this);
	}

}
