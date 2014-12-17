package final_2.logics.grammar.expression.binary.asgn;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Asgn_Op;

public abstract class Asgn_Expr<T> extends Expr<T> {
	
	private Idnt<T> identifier;
	
	private Asgn_Op<T> operator;
	
	private Expr<T> expression;

	public Asgn_Expr(Idnt<T> identifier, Asgn_Op<T> operator, Expr<T> expression) {
		super();
		this.identifier = identifier;
		this.operator = operator;
		this.expression = expression;
	}

	public Idnt<T> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Idnt<T> identifier) {
		this.identifier = identifier;
	}

	public Asgn_Op<T> getOperator() {
		return operator;
	}

	public void setOperator(Asgn_Op<T> operator) {
		this.operator = operator;
	}

	public Expr<T> getExpression() {
		return expression;
	}

	public void setExpression(Expr<T> expression) {
		this.expression = expression;
	}
	
	@Override
	public String toString() {
		return getIdentifier().toString() + getOperator().toString() + getExpression().toString();
	}
}
