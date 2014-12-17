package final_2.logics.grammar.expression.binary.varDec;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.binary.asgn.Asgn_Expr;
import final_2.logics.grammar.type.Type;

public abstract class Var_Dec_Expr<T> extends Expr<T>{

	private Type type;
	
	private Asgn_Expr<T> expression;
	
	public Var_Dec_Expr(Type type, Asgn_Expr<T> expression) {
		this.type=type;
		this.expression=expression;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Asgn_Expr<T> getExpression() {
		return expression;
	}

	public void setExpression(Asgn_Expr<T> expression) {
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		return getType().toString() + getExpression().toString();
	}
}
