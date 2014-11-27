package logics.grammar.statements;

import java.util.LinkedList;

public class Block {
	
	private LinkedList<Statement> statements;

	public Block() {
		statements= new LinkedList<Statement>();
	}
	
	public LinkedList<Statement> getStatements() {
		return statements;
	}

	/*
	public void setStatements(LinkedList<Statement> statements) {
		this.statements = statements;
	}
	*/
	
	public void addStatemnt(Statement s){
		statements.addLast(s);
	}

}
