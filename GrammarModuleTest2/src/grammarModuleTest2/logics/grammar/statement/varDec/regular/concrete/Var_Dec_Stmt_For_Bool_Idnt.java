package grammarModuleTest2.logics.grammar.statement.varDec.regular.concrete;

import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.statement.varDec.regular.Var_Dec_Stmt_For_Idnt;
import grammarModuleTest2.logics.grammar.type.Primitive_Type;
import grammarModuleTest2.main.ScopeTable;

public class Var_Dec_Stmt_For_Bool_Idnt extends Var_Dec_Stmt_For_Idnt<Boolean>{

	public Var_Dec_Stmt_For_Bool_Idnt(Idnt<Boolean> identifier) {
		super(new Primitive_Type(Primitive_Type.Primitive.Boolean), identifier);
	}

	@Override
	public void setDefault() {
		ScopeTable.BoolScope.put(getIdentifier().getName(), false);
	}

	@Override
	public void run() {
		if(!ScopeTable.BoolScope.containsKey(getIdentifier().getName())){ // TODO
			setDefault();
		}
	}

}
