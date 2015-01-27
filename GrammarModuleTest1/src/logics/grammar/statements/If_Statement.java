package logics.grammar.statements;

import logics.grammar.expression.Expression;
import logics.visitor.CodeVisitor;

public class If_Statement extends Statement {
	
	private Expression condition;

    private Statement thenStmt;

    private Statement elseStmt;

	public If_Statement() {
		super("if");
	}

    public If_Statement(Expression condition, Statement thenStmt, Statement elseStmt) {
    	super("if");
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
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

	public Statement getThenStmt() {
		return thenStmt;
	}

	public void setThenStmt(Statement thenStmt) {
		this.thenStmt = thenStmt;
	}

	public Statement getElseStmt() {
		return elseStmt;
	}

	public void setElseStmt(Statement elseStmt) {
		this.elseStmt = elseStmt;
	}

}
