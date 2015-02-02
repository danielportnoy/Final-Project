package grammarModuleTest2.logics.grammar.statement.varDec.asgn.concrete;

import grammarModuleTest2.logics.grammar.statement.asgn.Bin_Asgn_Stmt;
import grammarModuleTest2.logics.grammar.statement.varDec.asgn.Var_Dec_Asgn_Stmt;
import grammarModuleTest2.logics.grammar.type.Primitive_Type;
import grammarModuleTest2.main.ScopeTable;

public class Var_Dec_Asgn_For_Int extends Var_Dec_Asgn_Stmt<Integer>{

	public Var_Dec_Asgn_For_Int(Bin_Asgn_Stmt<Integer> assignment) {
		super(new Primitive_Type(Primitive_Type.Primitive.Int), assignment);
	}

	@Override
	public void run() {
		if(!ScopeTable.IntScope.containsKey(getAssignment().getIdentifier().getName())){ // TODO
			setDefault();
			getAssignment().run();
		}
	}

	@Override
	public void setDefault() {
		ScopeTable.IntScope.put(getAssignment().getIdentifier().getName(),0);
	}

}
