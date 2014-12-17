package final_2.logics.grammar.expression.unary.concrete;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.unary.Int_Un_Expr;
import final_2.logics.grammar.operator.unary.Un_Op;

public class Int_Un_Expr_For_Int extends Int_Un_Expr<Integer>{

	public Int_Un_Expr_For_Int(Expr<Integer> operand,Un_Op<Integer, Integer> operator) {
		super(operand, operator);
	}
}
