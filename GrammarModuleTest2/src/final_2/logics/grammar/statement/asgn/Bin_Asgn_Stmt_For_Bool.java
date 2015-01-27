package final_2.logics.grammar.statement.asgn;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Bin_Asgn_Op;

public abstract class Bin_Asgn_Stmt_For_Bool extends Bin_Asgn_Stmt<Boolean>{

	public Bin_Asgn_Stmt_For_Bool(Idnt<Boolean> identifier,Bin_Asgn_Op<Boolean> operator, Expr<Boolean> expression) {
		super(identifier, operator, expression);
	}

}
