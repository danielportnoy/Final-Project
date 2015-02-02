package grammarModuleTest2.logics.grammar.statement.varDec.asgn.concrete;

import grammarModuleTest2.logics.grammar.statement.asgn.Bin_Asgn_Stmt;
import grammarModuleTest2.logics.grammar.statement.varDec.asgn.Var_Dec_Asgn_Stmt;
import grammarModuleTest2.logics.grammar.type.Primitive_Type;
import grammarModuleTest2.main.ScopeTable;

public class Var_Dec_Asgn_For_Bool extends Var_Dec_Asgn_Stmt<Boolean>{

	public Var_Dec_Asgn_For_Bool(Bin_Asgn_Stmt<Boolean> expression) {
		super(new Primitive_Type(Primitive_Type.Primitive.Boolean), expression);
	}

	@Override
	public void run() {
		if(!ScopeTable.BoolScope.containsKey(getAssignment().getIdentifier().getName())){ // TODO
			setDefault();
			getAssignment().run();
		}
	}

	@Override
	public void setDefault() {
		ScopeTable.BoolScope.put(getAssignment().getIdentifier().getName(),false);
	}

}
