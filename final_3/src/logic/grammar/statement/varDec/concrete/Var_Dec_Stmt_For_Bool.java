package logic.grammar.statement.varDec.concrete;

import logic.grammar.ScopeTable;
import logic.grammar.expression.Bool_Expr;
import logic.grammar.identifier.Bool_Idnt;
import logic.grammar.statement.varDec.Var_Dec_Stmt;
import logic.grammar.type.Primitive_Type;

public class Var_Dec_Stmt_For_Bool extends Var_Dec_Stmt{

	private boolean isAsgn = false;

	private Bool_Idnt identifier = null;

	private Bool_Expr rightBool = null;

	public Var_Dec_Stmt_For_Bool(Bool_Idnt identifier) {
		super(new Primitive_Type(Primitive_Type.Primitive.Boolean));
		this.isAsgn = false;
		this.identifier = identifier;
	}

	public Var_Dec_Stmt_For_Bool(Bool_Idnt identifier, Bool_Expr rightBool) {
		super(new Primitive_Type(Primitive_Type.Primitive.Boolean));
		this.isAsgn = true;
		this.identifier = identifier;
		this.rightBool = rightBool;

	}

	@Override
	public void setDefault() {
		ScopeTable.BoolScope.put(identifier.getName(),false);
	}

	@Override
	public Void run() {
		setDefault();

		if(isAsgn)
			identifier.setValue(rightBool.run());

		return null;
	}

	@Override
	public String toString() {
		if(isAsgn)
			return getType().toString() + " " + identifier.toString() + " = " + rightBool.toString();
		else
			return getType().toString() + " " + identifier.toString();
	}

}
