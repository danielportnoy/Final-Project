package final_2.logics.grammar.statement;

import java.util.LinkedList;

import final_2.logics.grammar.expression.Bool_Expr;

public class For_Stmt extends Stmt {

	private LinkedList<Stmt> forInit;

	private Bool_Expr condition;

	private LinkedList<Stmt> forUpdate;

	private Stmt body;

	public For_Stmt( LinkedList<Stmt> forInit, Bool_Expr condition, LinkedList<Stmt> forUpdate, Stmt body) {
		this.forInit = forInit;
		this.condition = condition;
		this.forUpdate = forUpdate;
		this.body = body;
	}

	public LinkedList<Stmt> getForInit() {
		return forInit;
	}

	public void setForInit(LinkedList<Stmt> forInit) {
		this.forInit = forInit;
	}

	public Bool_Expr getCondition() {
		return condition;
	}

	public void setCondition(Bool_Expr condition) {
		this.condition = condition;
	}

	public LinkedList<Stmt> getForUpdate() {
		return forUpdate;
	}

	public void setForUpdate(LinkedList<Stmt> forUpdate) {
		this.forUpdate = forUpdate;
	}

	public Stmt getBody() {
		return body;
	}

	public void setBody(Stmt body) {
		this.body = body;
	}

	@Override
	public void run() {

		for (Stmt stmt : getForInit()) {
			stmt.run();
		}

		while(getCondition().run()){

			getBody().run();

			for (Stmt stmt : getForUpdate()) {
				stmt.run();
			}
		}
	}

	@Override
	public String toString() {

		String res = "for " + "(" ;

		for (int i = 0; i < getForInit().size() ; i++) {

			res += getForInit().get(i).toString();

			if(i+1< getForInit().size())
				res += ",";
		}

		res += " ; " + getCondition().toString() + " ; ";

		for (int i = 0; i < getForUpdate().size() ; i++) {

			res += getForUpdate().get(i).toString();

			if(i+1< getForUpdate().size())
				res += ",";
		}

		res += ")" + " {" + "\n";

		res += getBody().toString() + "\n" + "}";

		res += "\n";

		return res;
	}
}
