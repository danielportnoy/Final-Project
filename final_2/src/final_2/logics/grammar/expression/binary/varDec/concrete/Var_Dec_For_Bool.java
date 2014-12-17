package final_2.logics.grammar.expression.binary.varDec.concrete;

import final_2.logics.grammar.expression.binary.asgn.Asgn_Expr;
import final_2.logics.grammar.expression.binary.varDec.Var_Dec_Expr;
import final_2.logics.grammar.type.Primitive_Type;

public class Var_Dec_For_Bool extends Var_Dec_Expr<Boolean>{

	public Var_Dec_For_Bool(Asgn_Expr<Boolean> expression) {
		super(new Primitive_Type(Primitive_Type.Primitive.Boolean), expression);
	}

	@Override
	public Boolean run() {
		getExpression().run();
		return null;
	}

}
