package final_2.logics.grammar.expression.binary.varDec.concrete;

import final_2.logics.grammar.expression.binary.asgn.Asgn_Expr;
import final_2.logics.grammar.expression.binary.varDec.Var_Dec_Expr;
import final_2.logics.grammar.type.Primitive_Type;

public class Var_Dec_For_Int extends Var_Dec_Expr<Integer>{

	public Var_Dec_For_Int(Asgn_Expr<Integer> expression) {
		super(new Primitive_Type(Primitive_Type.Primitive.Int), expression);
	}

	@Override
	public Integer run() {
		getExpression().run();
		return null;
	}

}
