package logics.grammar.expression;

import logics.grammar.operators.Integer_Binary_Operator;
import logics.visitor.CodeVisitor;

public class Integer_Binary_Expression extends Binary_Expression {

	public Integer_Binary_Expression(Expression left, Expression right,Integer_Binary_Operator op) {
		super(left, right, op);
	}

	@Override
	public Integer accept(CodeVisitor v) {
		return v.visit(this);
	}

}
