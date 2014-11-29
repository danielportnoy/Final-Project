package logics.visitor;

import logics.grammar.literals.Boolean_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.Statement;


public class CodeVisitorElement implements CodeVisitor {

	@Override
	public boolean visit(Boolean_Literal boolean_Literal) {
		return boolean_Literal.getValue();
	}

	@Override
	public void visit(If_Statement if_Statement) {
		
		boolean ret = if_Statement.getCondition().accept(this);
		
		if(if_Statement.getThenStmt()!=null && ret == true)
			if_Statement.getThenStmt().accept(this);
		else if(if_Statement.getElseStmt()!=null && ret == false)
			if_Statement.getElseStmt().accept(this);
		
		return;
	}

	@Override
	public void visit(Block block) {
		for (Statement statement : block.getStatements()) {
			statement.accept(this);
		}

	}
}
