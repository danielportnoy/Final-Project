package final_2.logics.grammar.statement.varDec.concrete;

import final_2.logics.grammar.statement.asgn.Asgn_Stmt;
import final_2.logics.grammar.statement.varDec.Var_Dec_Stmt;
import final_2.logics.grammar.type.Primitive_Type;
import final_2.main.ScopeTable;

public class Var_Dec_For_Bool extends Var_Dec_Stmt<Boolean>{

	public Var_Dec_For_Bool(Asgn_Stmt<Boolean> expression) {
		super(new Primitive_Type(Primitive_Type.Primitive.Boolean), expression);
	}

	@Override
	public void run() {
		if(!ScopeTable.BoolScope.containsKey(getExpression().getIdentifier().getName())) // TODO
			getExpression().run();
	}

}
