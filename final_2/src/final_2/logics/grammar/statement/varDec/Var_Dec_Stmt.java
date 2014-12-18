package final_2.logics.grammar.statement.varDec;

import final_2.logics.grammar.statement.Stmt;
import final_2.logics.grammar.statement.asgn.Asgn_Stmt;
import final_2.logics.grammar.type.Type;

public abstract class Var_Dec_Stmt<T> extends Stmt{

	private Type type;
	
	private Asgn_Stmt<T> expression;
	
	public Var_Dec_Stmt(Type type, Asgn_Stmt<T> expression) {
		super(""); // TODO
		this.type=type;
		this.expression=expression;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Asgn_Stmt<T> getExpression() {
		return expression;
	}

	public void setExpression(Asgn_Stmt<T> expression) {
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		return getType().toString() + getExpression().toString();
	}
}
