package final_2.logics.grammar.statement;

import final_2.logics.grammar.expression.Bool_Expr;

public class If_Stmt extends Stmt {
	
	private Bool_Expr condition;
	
	private Stmt thenBody;
	
	private Stmt elseBody;
	
	public If_Stmt(Bool_Expr condition, Stmt thenBody, Stmt elseBody) {
		super("if");
		this.condition=condition;
		this.thenBody=thenBody;
		this.elseBody = elseBody;
	}

	public Bool_Expr getCondition() {
		return condition;
	}

	public void setCondition(Bool_Expr condition) {
		this.condition = condition;
	}

	public Stmt getThenBody() {
		return thenBody;
	}

	public void setThenBody(Stmt thenBody) {
		this.thenBody = thenBody;
	}

	public Stmt getElseBody() {
		return elseBody;
	}

	public void setElseBody(Stmt elseBody) {
		this.elseBody = elseBody;
	}

	@Override
	public void run() {
		if(condition.run())
			thenBody.run();
		else
			elseBody.run();
	}
}
