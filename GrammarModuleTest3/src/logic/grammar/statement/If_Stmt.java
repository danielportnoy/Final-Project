package logic.grammar.statement;

import logic.grammar.expression.Bool_Expr;

public class If_Stmt extends Stmt {
	
	private Bool_Expr condition;
	
	private Stmt thenBody;
	
	private Stmt elseBody;
	
	public If_Stmt(Bool_Expr condition, Stmt thenBody, Stmt elseBody) {
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
	public Void run() {
		if(condition.run())
			thenBody.run();
		else
			elseBody.run();
		
		return null;
	}

	@Override
	public String toString() {

		/*String res = "if " + "(" + getCondition().toString() + ")" + " {" + "\n";
		
		res += getThenBody().toString() + "\n" + "}";
		
		res += "else " + " {" +"\n";
		
		res += getElseBody().toString() + "\n" + "}";
		
		res+= "\n";
		
		return res;*/
		
		return null; // TODO
	}
}
