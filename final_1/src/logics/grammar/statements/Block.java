package logics.grammar.statements;

import java.util.LinkedList;

import logics.visitor.CodeVisitor;

public class Block extends Statement{

	private LinkedList<Statement> statements;

	public Block() {
		super("");
		statements= new LinkedList<Statement>();
	}

	/*
	public void setStatements(LinkedList<Statement> statements) {
		this.statements = statements;
	}
	*/

	public LinkedList<Statement> getStatements() {
		return statements;
	}

	public void addStatemnt(Statement s){
		statements.addLast(s);
	}

	@Override
	public Void accept(CodeVisitor v) {
		v.visit(this);
		return null;
	}

}
