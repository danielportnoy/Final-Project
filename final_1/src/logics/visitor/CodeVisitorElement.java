package logics.visitor;

import logics.grammar.expression.Binary_Expression;
import logics.grammar.expression.Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.Statement;


public class CodeVisitorElement implements CodeVisitor {

	@Override
	public Boolean_Literal visit(Boolean_Literal boolean_Literal) {
		return boolean_Literal;
	}

	@Override
	public void visit(If_Statement if_Statement) {

		Expression conditionResult = (Expression)if_Statement.getCondition().accept(this);
		
		boolean res = ((Boolean_Literal)conditionResult).getValue();

		if(if_Statement.getThenStmt()!=null && res == true)
			if_Statement.getThenStmt().accept(this);
		else if(if_Statement.getElseStmt()!=null && res == false)
			if_Statement.getElseStmt().accept(this);

		return;
	}

	@Override
	public void visit(Block block) {
		for (Statement statement : block.getStatements()) {
			statement.accept(this);
		}

	}

	@Override
	public Expression visit(Binary_Expression binary_Expression) {

		Expression right = binary_Expression.getRight();
		Expression left = binary_Expression.getLeft();

		Binary_Expression.Operator op = binary_Expression.getOp();

		Expression r = (Expression) right.accept(this);
		Expression l = (Expression) left.accept(this);

		if(op == Binary_Expression.Operator.plus)
			return new Integer_Literal(
					((Integer_Literal)r).getValue()
					+
					((Integer_Literal)l).getValue()
					);
		else if(op == Binary_Expression.Operator.equals)
			if(((int)((Integer_Literal)r).getValue())==((int)((Integer_Literal)l).getValue()))
				return new Boolean_Literal(true);
			else
				return new Boolean_Literal(false);
		else
			return null;
	}

	@Override
	public Integer_Literal visit(Integer_Literal integer_Literal) {
		return integer_Literal;
	}
}
