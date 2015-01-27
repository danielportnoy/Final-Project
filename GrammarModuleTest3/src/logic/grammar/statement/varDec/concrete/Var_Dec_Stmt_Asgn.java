package logic.grammar.statement.varDec.concrete;

import logic.grammar.ScopeTable;
import logic.grammar.expression.Bool_Expr;
import logic.grammar.expression.Int_Expr;
import logic.grammar.identifier.Bool_Idnt;
import logic.grammar.identifier.Int_Idnt;
import logic.grammar.statement.varDec.Var_Dec_Stmt;
import logic.grammar.type.Bool_Type;
import logic.grammar.type.Int_Type;

public class Var_Dec_Stmt_Asgn extends Var_Dec_Stmt{

	private boolean isForInt = false;
	private boolean isForBool = false;
	
	private Int_Idnt identifierInt = null;
	private Int_Expr rightInt = null;

	private Bool_Idnt identifierBool = null;
	private Bool_Expr rightBool = null;

	public Var_Dec_Stmt_Asgn(Int_Idnt identifier , Int_Expr rightInt) {
		super(new Int_Type());
		this.isForInt = true;
		this.identifierInt = identifier;
		this.rightInt = rightInt;
	}

	public Var_Dec_Stmt_Asgn(Bool_Idnt identifier , Bool_Expr rightBool) {
		super(new Bool_Type());
		this.isForBool = true;
		this.identifierBool = identifier;
		this.rightBool = rightBool;
	}
	

	@Override
	public Void run() {
		
		if(isForInt)
			ScopeTable.IntScope.put(identifierInt.getName(),rightInt.run());
		else if(isForBool)
			ScopeTable.BoolScope.put(identifierBool.getName(),rightBool.run());

		return null;
	}

	@Override
	public String toString() {
		if(isForInt)
			return getType().toString() + " " + identifierInt.toString() + " = " + rightInt.toString();
		else
			return getType().toString() + " " + identifierBool.toString() + " = " + rightBool.toString();
	}
}
