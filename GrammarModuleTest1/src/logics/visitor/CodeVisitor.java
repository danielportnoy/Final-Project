package logics.visitor;

import logics.grammar.Identifier;
import logics.grammar.StatementExpression;
import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.expression.Integer_Unary_Expression;
import logics.grammar.expression.VariableDeclaration_Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.statements.Block_Statement;
import logics.grammar.statements.For_Statement;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.While_Statement;

public interface CodeVisitor {

	void visit(Block_Statement block);

	Boolean visit(Boolean_Literal boolean_Literal);
	
	Integer visit(Integer_Literal integer_Literal);

	void visit(If_Statement if_Statement);

	Boolean visit(Boolean_Binary_Expression boolean_binary_Expression);

	Integer visit(Integer_Binary_Expression integer_Binary_Expression);

	void visit(While_Statement while_Statement);

	void visit(VariableDeclaration_Expression variableDeclaration_Statement);

	String visit(Identifier identifier);

	Integer visit(Integer_Unary_Expression integer_Unary_Expression);

	void visit(StatementExpression statementExpression);

	void visit(For_Statement for_Statement);

}
