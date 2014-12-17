package final_2.logics.grammar.expression.unary;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.operator.unary.Un_Op;

public abstract class Int_Un_Expr<T> extends Un_Expr<Integer, T> {

	public Int_Un_Expr(Expr<T> operand, Un_Op<Integer, T> operator) {
		super(operand, operator);
	}

}
