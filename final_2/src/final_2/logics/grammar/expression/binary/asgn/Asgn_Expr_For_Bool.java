package final_2.logics.grammar.expression.binary.asgn;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Asgn_Op;

public abstract class Asgn_Expr_For_Bool extends Asgn_Expr<Boolean>{

	public Asgn_Expr_For_Bool(Idnt<Boolean> identifier,Asgn_Op<Boolean> operator, Expr<Boolean> expression) {
		super(identifier, operator, expression);
	}

}
