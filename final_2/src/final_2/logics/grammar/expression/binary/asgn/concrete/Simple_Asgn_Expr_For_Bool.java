package final_2.logics.grammar.expression.binary.asgn.concrete;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.binary.asgn.Asgn_Expr_For_Bool;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Asgn_Op;

public class Simple_Asgn_Expr_For_Bool extends Asgn_Expr_For_Bool{

	public Simple_Asgn_Expr_For_Bool(Idnt<Boolean> identifier,Asgn_Op<Boolean> operator, Expr<Boolean> expression) {
		super(identifier, operator, expression);
	}

	@Override
	public Boolean run() {
		getIdentifier().setValue(getExpression().run());
		return null;
	}

}
