package logics.visitor;

import java.util.LinkedList;

import logics.grammar.Identifier;
import logics.grammar.StatementExpression;
import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.expression.Integer_Unary_Expression;
import logics.grammar.expression.VariableDeclaration_Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.operators.Boolean_Binary_Operator;
import logics.grammar.operators.Integer_Binary_Operator;
import logics.grammar.operators.Integer_Unary_Operator;
import logics.grammar.statements.Block_Statement;
import logics.grammar.statements.For_Statement;
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
	public void visit(VariableDeclaration_Expression variableDeclaration_Expression) {

		if(variableDeclaration_Expression.getIdentifier()!=null && variableDeclaration_Expression.getInitStmt()!=null){
			String id = variableDeclaration_Expression.getIdentifier().getName();

			if(id!=null)
				DB.db.put(id, variableDeclaration_Expression.getInitStmt().accept(this));
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

	@Override
	public Integer visit(Integer_Unary_Expression integer_Unary_Expression) {

		Expression exp = integer_Unary_Expression.getExp();

		Integer_Unary_Operator op = (Integer_Unary_Operator) integer_Unary_Expression.getOp();

		Object e = extractExpression(exp);

		if(op != null && e != null)
			return op.operate(e , exp);
		else
			return null;	
	}

	@Override
	public void visit(StatementExpression statementExpression) {
		statementExpression.getExpression().accept(this);
	}

	@Override
	public void visit(For_Statement for_Statement) {

		LinkedList<Expression> forInit = for_Statement.getForInit();
		Expression condition = for_Statement.getCondition();
		LinkedList<Expression> forUpdate = for_Statement.getForUpdate();
		Statement body = for_Statement.getBody();

		if (forInit != null){
			for (int i = 0; i < forInit.size(); i++)
				forInit.get(i).accept(this);
		}


		if (condition != null) {

			while((boolean) extractExpression(condition)){ //TODO

				if(body != null)
					body.accept(this);
				
				if (forUpdate != null) {
					for (int i = 0; i < forUpdate.size(); i++)
						forUpdate.get(i).accept(this);
				}
			}
		}
	}
}
