package grammarModuleTest2.logics.grammar.statement.statement_expression;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.statement.Stmt;

public abstract class Stmt_Expr<T> extends Stmt {
	
	private Expr<T> expression;

	public Stmt_Expr(Expr<T> expression) {
		this.expression=expression;
	}

	public Expr<T> getExpression() {
		return expression;
	}

	public void setExpression(Expr<T> expression) {
		this.expression = expression;
	}
	
	@Override
	public void run() {
		getExpression().run();
	}
	
	@Override
	public String toString() {
		return getExpression().toString();
	}
}
