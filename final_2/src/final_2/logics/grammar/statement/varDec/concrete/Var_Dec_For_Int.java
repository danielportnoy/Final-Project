package final_2.logics.grammar.statement.varDec.concrete;

import final_2.logics.grammar.statement.asgn.Asgn_Stmt;
import final_2.logics.grammar.statement.varDec.Var_Dec_Stmt;
import final_2.logics.grammar.type.Primitive_Type;
import final_2.main.ScopeTable;

public class Var_Dec_For_Int extends Var_Dec_Stmt<Integer>{

	public Var_Dec_For_Int(Asgn_Stmt<Integer> expression) {
		super(new Primitive_Type(Primitive_Type.Primitive.Int), expression);
	}

	@Override
	public void run() {
		if(!ScopeTable.IntScope.containsKey(getExpression().getIdentifier().getName())) // TODO
			getExpression().run();
	}

}
