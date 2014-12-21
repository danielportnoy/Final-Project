package final_2.logics.grammar.statement;

import final_2.logics.grammar.expression.Bool_Expr;

public class While_Stmt extends Stmt{

	private Bool_Expr condition;

	private Stmt body;

	public While_Stmt(Bool_Expr condition, Stmt body) {
		this.condition=condition;
		this.body=body;
	}

	public Bool_Expr getCondition() {
		return condition;
	}

	public void setCondition(Bool_Expr condition) {
		this.condition = condition;
	}

	public Stmt getBody() {
		return body;
	}

	public void setBody(Stmt body) {
		this.body = body;
	}

	@Override
	public void run() {
		while(getCondition().run())
			getBody().run();
	}

	@Override
	public String toString() {

		String res = "while " + "(" + getCondition().toString() + ")" + " {" + "\n";

		res += getBody().toString() + "\n" + "}";

		res+= "\n";

		return res;
	}

}
