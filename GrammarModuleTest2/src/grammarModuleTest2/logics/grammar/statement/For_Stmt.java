package grammarModuleTest2.logics.grammar.statement;

import java.util.LinkedList;

import grammarModuleTest2.logics.grammar.expression.Bool_Expr;

public class For_Stmt extends Stmt {

	private LinkedList<Stmt> forInit;

	private Bool_Expr condition;

	private LinkedList<Stmt> forUpdate;

	private Block_Stmt body;

	public For_Stmt( LinkedList<Stmt> forInit, Bool_Expr condition, LinkedList<Stmt> forUpdate, Block_Stmt body) {
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

	public Block_Stmt getBody() {
		return body;
	}

	public void setBody(Block_Stmt body) {
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

		res += ")";

		res += getBody().toString();

		return res;
	}
}
