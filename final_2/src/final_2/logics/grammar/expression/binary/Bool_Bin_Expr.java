package final_2.logics.grammar.expression.binary;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.operator.binary.Bin_Op;

public abstract class Bool_Bin_Expr<T> extends Bin_Expr<Boolean, T>{

	public Bool_Bin_Expr(Expr<T> left, Bin_Op<Boolean, T> operator,Expr<T> right) {
		super(left, operator, right);
	}

}
