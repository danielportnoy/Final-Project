package grammarModuleTest2.logics.grammar.statement.varDec.regular.concrete;

import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.statement.varDec.regular.Var_Dec_Stmt_For_Idnt;
import grammarModuleTest2.logics.grammar.type.Primitive_Type;
import grammarModuleTest2.main.ScopeTable;

public class Var_Dec_Stmt_For_Int_Idnt extends Var_Dec_Stmt_For_Idnt<Integer> {

	public Var_Dec_Stmt_For_Int_Idnt(Idnt<Integer> identifier) {
		super(new Primitive_Type(Primitive_Type.Primitive.Int), identifier);
	}

	@Override
	public void setDefault() {
		ScopeTable.IntScope.put(getIdentifier().getName(),0);
	}

	@Override
	public void run() {
		if(!ScopeTable.IntScope.containsKey(getIdentifier().getName())){ // TODO
			setDefault();
		}
	}

}
