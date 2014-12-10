package logics.grammar.operators;

import logics.grammar.expression.Expression;

public abstract class Unary_Operator<R> extends Operator {

	public static enum UnaryOperatorEnum {
		positive, // +
		negative, // -
		preIncrement, // ++
		preDecrement, // --
		not, // !
		inverse, // ~
		posIncrement, // ++
		posDecrement, // --
	}

	private UnaryOperatorEnum operator;

	public Unary_Operator(UnaryOperatorEnum operator) {
		this.operator = operator;
	}

	public UnaryOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(UnaryOperatorEnum operator) {
		this.operator = operator;
	}

	public abstract R operate(R operand , Expression exp);

}
