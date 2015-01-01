package logic.grammar.statement;

import java.util.LinkedList;

public class Block_Stmt extends Stmt {

	private LinkedList<Stmt> statements;

	public Block_Stmt() {
		statements = new LinkedList<Stmt>();
	}

	public Block_Stmt(LinkedList<Stmt> statements) {
		this.statements=statements;
	}

	public LinkedList<Stmt> getStatements() {
		return statements;
	}

	public void setStatements(LinkedList<Stmt> statements) {
		this.statements = statements;
	}

	@Override
	public Void run() {
		for (Stmt stmt : statements) {
			stmt.run();
		}
		return null;
	}

	@Override
	public String toString() {

		String res = "";

		for (int i = 0; i < getStatements().size(); i++) {
			res += "\t" +getStatements().get(i).toString();
			if( i+1 < getStatements().size() )
				res += "\n";
		}

		return res;
	}
}
