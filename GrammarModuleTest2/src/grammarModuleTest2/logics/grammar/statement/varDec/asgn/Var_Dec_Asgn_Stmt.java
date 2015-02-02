package grammarModuleTest2.logics.grammar.statement.varDec.asgn;

import grammarModuleTest2.logics.grammar.statement.asgn.Bin_Asgn_Stmt;
import grammarModuleTest2.logics.grammar.statement.varDec.Var_Dec_Stmt;
import grammarModuleTest2.logics.grammar.type.Type;

public abstract class Var_Dec_Asgn_Stmt<T> extends Var_Dec_Stmt {

	private Bin_Asgn_Stmt<T> assignment;

	public Var_Dec_Asgn_Stmt( Type type , Bin_Asgn_Stmt<T> assignment) {
		super(type);
		this.assignment = assignment;
	}
	
	public Bin_Asgn_Stmt<T> getAssignment() {
		return assignment;
	}

	public void setAssignment(Bin_Asgn_Stmt<T> assignment) {
		this.assignment = assignment;
	}
	
	@Override
	public String toString() {
		return getType().toString() + " " + getAssignment().toString();
	}
}
