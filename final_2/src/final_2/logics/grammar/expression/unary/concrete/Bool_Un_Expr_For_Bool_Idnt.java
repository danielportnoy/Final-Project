package final_2.logics.grammar.expression.unary.concrete;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.expression.unary.Un_Expr_For_Idnt;
import final_2.logics.grammar.operator.unary.idnt.Un_Op_For_Bool_Idnt;

public class Bool_Un_Expr_For_Bool_Idnt extends Un_Expr_For_Idnt<Boolean>{
	
	public Bool_Un_Expr_For_Bool_Idnt(Idnt<Boolean> identifier,Un_Op_For_Bool_Idnt operator) {
		super(identifier, operator);
	}
}
