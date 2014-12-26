package final_2.logics.grammar.statement.asgn.concrete;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.concrete.Simple_Asgn_For_Bool;
import final_2.logics.grammar.statement.asgn.Bin_Asgn_Stmt_For_Bool;

public class Simple_Asgn_Stmt_For_Bool extends Bin_Asgn_Stmt_For_Bool{

	public Simple_Asgn_Stmt_For_Bool(Idnt<Boolean> identifier , Expr<Boolean> expression) {
		super(identifier, new Simple_Asgn_For_Bool(), expression);
	}

	@Override
	public void run() {
		getIdentifier().setValue(getExpression().run());
	}

}
