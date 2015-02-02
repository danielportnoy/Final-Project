package grammarModuleTest2.logics.grammar.statement;

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
	public void run() {
		for (Stmt stmt : statements) {
			stmt.run();
		}
	}

	@Override
	public String toString() {

		String res = "{" + "\n";

		for (Stmt stmt : statements) {
			/*res += "\t" + stmt.toString();*/
			res += "        " + stmt.toString();
		}

		res += "\n" + "}";

		return res;
	}
}
