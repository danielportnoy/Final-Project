package logics.grammar.expression;

import logics.grammar.operators.Integer_Unary_Operator;
import logics.visitor.CodeVisitor;

public class Integer_Unary_Expression extends Unary_Expression {

	public Integer_Unary_Expression(Expression exp, Integer_Unary_Operator op) {
		super(exp, op);
	}

	@Override
	public Integer accept(CodeVisitor v) {
		return v.visit(this);
	}

}
