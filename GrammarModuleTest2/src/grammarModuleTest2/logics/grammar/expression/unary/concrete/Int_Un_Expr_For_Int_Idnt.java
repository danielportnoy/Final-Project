package grammarModuleTest2.logics.grammar.expression.unary.concrete;

import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.expression.unary.Un_Expr_For_Idnt;
import grammarModuleTest2.logics.grammar.operator.unary.idnt.Un_Op_For_Int_Idnt;

public class Int_Un_Expr_For_Int_Idnt extends Un_Expr_For_Idnt<Integer> {

	public Int_Un_Expr_For_Int_Idnt(Idnt<Integer> identifier,Un_Op_For_Int_Idnt operator) {
		super(identifier, operator);
	}
}
