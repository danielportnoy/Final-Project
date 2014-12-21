package final_2.logics.grammar.statement.varDec.regular;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.statement.varDec.Var_Dec_Stmt;
import final_2.logics.grammar.type.Type;

public abstract class Var_Dec_Stmt_For_Idnt<T> extends Var_Dec_Stmt {
	
	private Idnt<T> identifier;

	public Var_Dec_Stmt_For_Idnt(Type type, Idnt<T> identifier) {
		super(type);
		this.identifier = identifier;
	}

	public Idnt<T> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Idnt<T> identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public String toString() {
		return getType().toString() + " " + getIdentifier().toString() + ";" + "\n";
	}

}
