package logic.grammar.statement.varDec.concrete;

import logic.grammar.ScopeTable;
import logic.grammar.identifier.Bool_Idnt;
import logic.grammar.identifier.Int_Idnt;
import logic.grammar.statement.varDec.Var_Dec_Stmt;
import logic.grammar.type.Bool_Type;
import logic.grammar.type.Int_Type;

public class Var_Dec_Stmt_Regular extends Var_Dec_Stmt{

	private boolean isForInt = false;
	private boolean isForBool = false;
	
	private Int_Idnt identifierInt = null;

	private Bool_Idnt identifierBool = null;

	public Var_Dec_Stmt_Regular(Int_Idnt identifier) {
		super(new Int_Type());
		this.isForInt = true;
		this.identifierInt = identifier;
	}

	public Var_Dec_Stmt_Regular(Bool_Idnt identifier) {
		super(new Bool_Type());
		this.isForBool = true;
		this.identifierBool = identifier;
	}
	

	@Override
	public Void run() {
		
		if(isForInt)
			ScopeTable.IntScope.put(identifierInt.getName(),0);
		else if(isForBool)
			ScopeTable.BoolScope.put(identifierBool.getName(),false);

		return null;
	}

	@Override
	public String toString() {
		if(isForInt)
			return getType().toString() + " " + identifierInt.toString();
		else
			return getType().toString() + " " + identifierBool.toString();
	}

}
