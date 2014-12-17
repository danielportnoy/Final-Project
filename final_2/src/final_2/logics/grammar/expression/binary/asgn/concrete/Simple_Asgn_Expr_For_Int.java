package final_2.logics.grammar.expression.binary.asgn.concrete;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.binary.asgn.Asgn_Expr_For_Int;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Asgn_Op;

public class Simple_Asgn_Expr_For_Int extends Asgn_Expr_For_Int {

	public Simple_Asgn_Expr_For_Int(Idnt<Integer> identifier,Asgn_Op<Integer> operator, Expr<Integer> expression) {
		super(identifier, operator, expression);
	}

	@Override
	public Integer run() {
		getIdentifier().setValue(getExpression().run());
		return null;
	}

}
