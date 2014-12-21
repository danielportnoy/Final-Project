package final_2.logics.grammar.expression.unary.concrete;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.expression.unary.Un_Expr_For_Idnt;
import final_2.logics.grammar.operator.unary.idnt.Un_Op_For_Int_Idnt;

public class Int_Un_Expr_For_Int_Idnt extends Un_Expr_For_Idnt<Integer> {

	public Int_Un_Expr_For_Int_Idnt(Idnt<Integer> identifier,Un_Op_For_Int_Idnt operator) {
		super(identifier, operator);
	}
}
