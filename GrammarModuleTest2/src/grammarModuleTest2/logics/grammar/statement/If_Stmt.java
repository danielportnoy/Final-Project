package grammarModuleTest2.logics.grammar.statement;

import grammarModuleTest2.logics.grammar.expression.Bool_Expr;

public class If_Stmt extends Stmt {
	
	/***** inner parts ****/
	// if condition
	private Bool_Expr condition;
	// if body
	private Stmt thenBody;
	// else body
	private Stmt elseBody;
	/***** inner parts ****/
	
	// constructor
	public If_Stmt(Bool_Expr condition, Stmt thenBody, Stmt elseBody) {
		this.condition=condition;
		this.thenBody=thenBody;
		this.elseBody = elseBody;
	}
	
	/***** getters and setters ****/
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
	/***** getters and setters ****/

	@Override
	public void run() {
		// test if condition is true
		if(condition.run())
			thenBody.run();// run the if body
		else
			elseBody.run();// run the else body
	}

	@Override
	public String toString() {

		String res = "if " + "(" + getCondition().toString() + ")" + " {" + "\n";
		
		res += getThenBody().toString() + "\n" + "}";
		
		res += "else " + " {" +"\n";
		
		res += getElseBody().toString() + "\n" + "}";
		
		res+= "\n";
		
		return res;
	}
}
