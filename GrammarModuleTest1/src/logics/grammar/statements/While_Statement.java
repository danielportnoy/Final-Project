package logics.grammar.statements;

import logics.grammar.expression.Expression;
import logics.visitor.CodeVisitor;

public class While_Statement extends Statement {

	private Expression condition;

	private Statement body;

	public While_Statement() {
		super("while");
	}

	public While_Statement(Expression condition, Statement body) {
		super("while");
		this.condition = condition;
		this.body = body;
	}

	@Override
	public Void accept(CodeVisitor v) {
		v.visit(this);
		return null;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

}
