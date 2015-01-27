package logics.grammar.statements;

import java.util.LinkedList;

import logics.grammar.expression.Expression;
import logics.visitor.CodeVisitor;

public class For_Statement extends Statement {

	private LinkedList<Expression> forInit;

	private Expression condition;

	private LinkedList<Expression> forUpdate;

	private Statement body;

	public For_Statement() {
		super("for");
	}

	public For_Statement ( LinkedList<Expression> forInit , Expression condition,  LinkedList<Expression> forUpdate , Statement body) {
		super("for");
		this.forInit = forInit;
		this.condition = condition;
		this.forUpdate = forUpdate;
		this.body = body;
	}

	@Override
	public Void accept(CodeVisitor v) {
		v.visit(this);
		return null;
	}

	public LinkedList<Expression> getForInit() {
		return forInit;
	}

	public void setForInit(LinkedList<Expression> forInit) {
		this.forInit = forInit;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public LinkedList<Expression> getForUpdate() {
		return forUpdate;
	}

	public void setForUpdate(LinkedList<Expression> forUpdate) {
		this.forUpdate = forUpdate;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

}
