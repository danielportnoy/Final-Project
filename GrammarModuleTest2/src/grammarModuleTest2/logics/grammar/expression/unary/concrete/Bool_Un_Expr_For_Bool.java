package grammarModuleTest2.logics.grammar.expression.unary.concrete;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.unary.Bool_Un_Expr;
import grammarModuleTest2.logics.grammar.operator.unary.Un_Op;

public class Bool_Un_Expr_For_Bool extends Bool_Un_Expr<Boolean> {

	public Bool_Un_Expr_For_Bool(Expr<Boolean> operand,Un_Op<Boolean, Boolean> operator) {
		super(operand, operator);
	}
}
