package grammarModuleTest2.logics.grammar.statement.asgn;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.operator.binary.asgn.Bin_Asgn_Op;
import grammarModuleTest2.logics.grammar.statement.Stmt;

public abstract class Bin_Asgn_Stmt<T> extends Stmt {
	
	private Idnt<T> identifier;
	
	private Bin_Asgn_Op<T> operator;
	
	private Expr<T> expression;

	public Bin_Asgn_Stmt(Idnt<T> identifier, Bin_Asgn_Op<T> operator, Expr<T> expression) {
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

	public Bin_Asgn_Op<T> getOperator() {
		return operator;
	}

	public void setOperator(Bin_Asgn_Op<T> operator) {
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
		return getIdentifier().toString() + " " + getOperator().toString() + " " + getExpression().toString();
	}
}
