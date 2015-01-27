package final_2.logics.grammar.expression.binary.concrete;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.binary.Int_Bin_Expr;
import final_2.logics.grammar.operator.binary.Int_Bin_Op;

public class Int_Bin_Expr_For_Int extends Int_Bin_Expr<Integer> {

	public Int_Bin_Expr_For_Int(Expr<Integer> left, Int_Bin_Op<Integer> operator, Expr<Integer> right) {
		super(left, operator, right);
	}
}
