package logics.visitor;

import logics.grammar.expression.Binary_Expression;
import logics.grammar.expression.Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;

public interface CodeVisitor {

	void visit(Block block);

	Boolean_Literal visit(Boolean_Literal boolean_Literal);

	void visit(If_Statement if_Statement);

	Expression visit(Binary_Expression binary_Expression);

	Integer_Literal visit(Integer_Literal integer_Literal);

}
