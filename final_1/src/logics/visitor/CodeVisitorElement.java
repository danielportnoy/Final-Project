package logics.visitor;

import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.Statement;


public class CodeVisitorElement implements CodeVisitor {

	@Override
	public Boolean visit(Boolean_Literal boolean_Literal) {
		return boolean_Literal.getValue();
	}
	
	@Override
	public Integer visit(Integer_Literal integer_Literal) {
		return integer_Literal.getValue();
	}

	@Override
	public void visit(If_Statement if_Statement) {

		//Expression conditionResult = (Expression)if_Statement.getCondition().accept(this);

		boolean res = (boolean) if_Statement.getCondition().accept(this);
		
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
	public Boolean visit(Boolean_Binary_Expression boolean_Binary_Expression) {

		Expression right = boolean_Binary_Expression.getRight();
		Expression left = boolean_Binary_Expression.getLeft();

		Boolean_Binary_Expression.Operator op = boolean_Binary_Expression.getOp();

		Object r = right.accept(this);
		Object l = left.accept(this);
 
		if(op == Boolean_Binary_Expression.Operator.equals)
			return r.equals(l);
		else
			return null;		
	}

	@Override
	public Integer visit(Integer_Binary_Expression integer_Binary_Expression) {
		
		Expression right = integer_Binary_Expression.getRight();
		Expression left = integer_Binary_Expression.getLeft();

		Boolean_Binary_Expression.Operator op = integer_Binary_Expression.getOp();

		Integer r = (Integer)right.accept(this);
		Integer l = (Integer)left.accept(this);

		if(op == Integer_Binary_Expression.Operator.plus)
			return r+l;		
		else
			return null;		
	}
}
