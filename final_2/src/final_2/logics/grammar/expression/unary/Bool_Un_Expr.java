package final_2.logics.grammar.expression.unary;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.operator.unary.Un_Op;

public abstract class Bool_Un_Expr<T> extends Un_Expr<Boolean, T>{

	public Bool_Un_Expr(Expr<T> operand, Un_Op<Boolean, T> operator) {
		super(operand, operator);
	}

}
