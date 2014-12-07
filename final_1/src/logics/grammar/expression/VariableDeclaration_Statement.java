package logics.grammar.expression;

import logics.grammar.Identifier;
import logics.grammar.statements.Statement;
import logics.grammar.types.Primitive_Type;
import logics.visitor.CodeVisitor;

public class VariableDeclaration_Statement extends Statement {

	private Primitive_Type type;

	private Identifier identifier;

	private Expression initStmt;

	public VariableDeclaration_Statement() {
		super("");
	}
	
	public VariableDeclaration_Statement(Primitive_Type type , Identifier identifier , Expression initStmt){
		super("");
		this.type = type;
		this.identifier = identifier;
		this.initStmt = initStmt;
	}

	@Override
	public Void accept(CodeVisitor v) {
		v.visit(this);
		return null;
	}

	public Primitive_Type getType() {
		return type;
	}

	public void setType(Primitive_Type type) {
		this.type = type;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public Expression getInitStmt() {
		return initStmt;
	}

	public void setInitStmt(Expression initStmt) {
		this.initStmt = initStmt;
	}

}
