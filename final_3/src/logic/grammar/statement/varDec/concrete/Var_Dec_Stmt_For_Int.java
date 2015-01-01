package logic.grammar.statement.varDec.concrete;

import logic.grammar.ScopeTable;
import logic.grammar.expression.Int_Expr;
import logic.grammar.identifier.Int_Idnt;
import logic.grammar.statement.varDec.Var_Dec_Stmt;
import logic.grammar.type.Primitive_Type;

public class Var_Dec_Stmt_For_Int extends Var_Dec_Stmt{

	private boolean isAsgn = false;

	private Int_Idnt identifier = null;

	private Int_Expr rightInt = null;

	public Var_Dec_Stmt_For_Int(Int_Idnt identifier) {
		super(new Primitive_Type(Primitive_Type.Primitive.Int));
		this.isAsgn = false;
		this.identifier = identifier;
	}

	public Var_Dec_Stmt_For_Int(Int_Idnt identifier, Int_Expr rightInt) {
		super(new Primitive_Type(Primitive_Type.Primitive.Int));
		this.isAsgn = true;
		this.identifier = identifier;
		this.rightInt = rightInt;

	}

	@Override
	public void setDefault() {
		ScopeTable.IntScope.put(identifier.getName(),0);
	}

	@Override
	public Void run() {
		setDefault();
		
		if(isAsgn)
			identifier.setValue(rightInt.run());

		return null;
	}

	@Override
	public String toString() {
		if(isAsgn)
			return getType().toString() + " " + identifier.toString() + " = " + rightInt.toString();
		else
			return getType().toString() + " " + identifier.toString();
	}
}
