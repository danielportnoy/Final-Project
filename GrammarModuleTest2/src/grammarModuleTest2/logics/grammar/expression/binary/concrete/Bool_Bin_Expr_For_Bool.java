package grammarModuleTest2.logics.grammar.expression.binary.concrete;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.binary.Bool_Bin_Expr;
import grammarModuleTest2.logics.grammar.operator.binary.Bool_Bin_Op;

public class Bool_Bin_Expr_For_Bool extends Bool_Bin_Expr<Boolean> {

	public Bool_Bin_Expr_For_Bool(Expr<Boolean> left, Bool_Bin_Op<Boolean> operator, Expr<Boolean> right) {
		super(left, operator, right);
	}
}