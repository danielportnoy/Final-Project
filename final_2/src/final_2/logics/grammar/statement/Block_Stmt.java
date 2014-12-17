package final_2.logics.grammar.statement;

import java.util.LinkedList;

public class Block_Stmt extends Stmt {
	
	private LinkedList<Stmt> statements;

	public Block_Stmt() {
		super("");
		statements = new LinkedList<Stmt>();
	}
	
	public Block_Stmt(LinkedList<Stmt> statements) {
		super("");
		this.statements=statements;
	}

	public LinkedList<Stmt> getStatements() {
		return statements;
	}

	public void setStatements(LinkedList<Stmt> statements) {
		this.statements = statements;
	}

	@Override
	public void run() {
		for (Stmt stmt : statements) {
			stmt.run();
		}
	}
}
