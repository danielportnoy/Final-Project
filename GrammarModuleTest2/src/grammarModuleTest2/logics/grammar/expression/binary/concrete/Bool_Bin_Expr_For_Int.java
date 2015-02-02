package grammarModuleTest2.logics.grammar.expression.binary.concrete;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.binary.Bool_Bin_Expr;
import grammarModuleTest2.logics.grammar.operator.binary.Bool_Bin_Op;

public class Bool_Bin_Expr_For_Int extends Bool_Bin_Expr<Integer> {

	public Bool_Bin_Expr_For_Int(Expr<Integer> left, Bool_Bin_Op<Integer> operator, Expr<Integer> right) {
		super(left, operator, right);
	}
}
