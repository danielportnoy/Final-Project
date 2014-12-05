package logics.visitor;

import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;

public interface CodeVisitor {

	void visit(Block block);

	Boolean visit(Boolean_Literal boolean_Literal);
	
	Integer visit(Integer_Literal integer_Literal);

	void visit(If_Statement if_Statement);

	Boolean visit(Boolean_Binary_Expression boolean_binary_Expression);

	Integer visit(Integer_Binary_Expression integer_Binary_Expression);

}
