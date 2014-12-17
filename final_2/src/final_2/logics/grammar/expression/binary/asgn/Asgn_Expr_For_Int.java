package final_2.logics.grammar.expression.binary.asgn;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Asgn_Op;

public abstract class Asgn_Expr_For_Int extends Asgn_Expr<Integer>{

	public Asgn_Expr_For_Int(Idnt<Integer> identifier,Asgn_Op<Integer> operator, Expr<Integer> expression) {
		super(identifier, operator, expression);
	}
}
