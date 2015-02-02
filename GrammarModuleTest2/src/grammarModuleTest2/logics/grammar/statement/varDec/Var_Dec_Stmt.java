package grammarModuleTest2.logics.grammar.statement.varDec;

import grammarModuleTest2.logics.grammar.statement.Stmt;
import grammarModuleTest2.logics.grammar.type.Type;

public abstract class Var_Dec_Stmt extends Stmt{

	private Type type;
	
	public Var_Dec_Stmt(Type type) {
		this.type=type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
		
	public abstract void setDefault(); 
}
