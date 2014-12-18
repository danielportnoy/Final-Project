package final_2.logics.grammar.statement.asgn.concrete;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Asgn_Op;
import final_2.logics.grammar.statement.asgn.Asgn_Stmt_For_Bool;

public class Simple_Asgn_Stmt_For_Bool extends Asgn_Stmt_For_Bool{

	public Simple_Asgn_Stmt_For_Bool(Idnt<Boolean> identifier,Asgn_Op<Boolean> operator, Expr<Boolean> expression) {
		super(identifier, operator, expression);
	}

	@Override
	public void run() {
		getIdentifier().setValue(getExpression().run());
	}

}
