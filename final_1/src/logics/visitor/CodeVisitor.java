package logics.visitor;

import logics.grammar.literals.Boolean_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;

public interface CodeVisitor {

	void visit(Block block);

	boolean visit(Boolean_Literal boolean_Literal);

	void visit(If_Statement if_Statement);

}
