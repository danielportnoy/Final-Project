package logics.grammar;

import logics.grammar.expression.Expression;
import logics.grammar.statements.Statement;
import logics.visitor.CodeVisitor;

public class StatementExpression extends Statement {

	private Expression expression;

	public StatementExpression(Expression expression) {
		super(expression.toString());
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Void accept(CodeVisitor v) {
		v.visit(this);
		return null;
	}

}
