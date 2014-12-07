package logics.visitor;

import logics.grammar.Identifier;
import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.expression.VariableDeclaration_Statement;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.operators.Boolean_Binary_Operator;
import logics.grammar.operators.Integer_Binary_Operator;
import logics.grammar.statements.Block_Statement;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.Statement;
import logics.grammar.statements.While_Statement;
import main.DB;


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
	public void visit(Block_Statement block) {
		for (Statement statement : block.getStatements()) {
			statement.accept(this);
		}

	}

	@Override
	public Boolean visit(Boolean_Binary_Expression boolean_Binary_Expression) {

		Expression right = boolean_Binary_Expression.getRight();
		Expression left = boolean_Binary_Expression.getLeft();

		Boolean_Binary_Operator op = (Boolean_Binary_Operator) boolean_Binary_Expression.getOp();

		Object r = extractExpression(right);
		Object l = extractExpression(left);

		if(op != null && r != null && l != null)
			return op.operate(r, l);
		else
			return null;	
	}

	@Override
	public Integer visit(Integer_Binary_Expression integer_Binary_Expression) {

		Expression right = integer_Binary_Expression.getRight();
		Expression left = integer_Binary_Expression.getLeft();

		Integer_Binary_Operator op = (Integer_Binary_Operator) integer_Binary_Expression.getOp();

		Object r = extractExpression(right);
		Object l = extractExpression(left);

		if(op != null && r != null && l != null)
			return op.operate(r, l);
		else
			return null;		
	}

	@Override
	public void visit(While_Statement while_Statement) {

		boolean res = (boolean) while_Statement.getCondition().accept(this);

		while(while_Statement.getBody()!=null && res == true){

			while_Statement.getBody().accept(this);

			res = (boolean) while_Statement.getCondition().accept(this);
		}

		return;
	}

	@Override
	public void visit(VariableDeclaration_Statement variableDeclaration_Statement) {

		if(variableDeclaration_Statement.getIdentifier()!=null && variableDeclaration_Statement.getInitStmt()!=null){
			String id = variableDeclaration_Statement.getIdentifier().getName();

			if(id!=null)
				DB.db.put(id, variableDeclaration_Statement.getInitStmt().accept(this));
		}
	}

	@Override
	public String visit(Identifier identifier) {
		return identifier.getName();
	}
	
	private Object extractExpression(Expression exp) {
		
		if(exp instanceof Identifier)
			return DB.db.get(exp.accept(this));
		else
			return exp.accept(this);
	}
}
