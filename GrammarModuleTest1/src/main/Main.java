package main;

import java.util.LinkedList;

import logics.game.Coordinate;
import logics.game.levels.level1.Level1;
import logics.grammar.Identifier;
import logics.grammar.StatementExpression;
import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.expression.Integer_Unary_Expression;
import logics.grammar.expression.VariableDeclaration_Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.operators.Binary_Operator.BinaryOperatorEnum;
import logics.grammar.operators.Boolean_Binary_Operator;
import logics.grammar.operators.Integer_Binary_Operator;
import logics.grammar.operators.Integer_Unary_Operator;
import logics.grammar.operators.Unary_Operator.UnaryOperatorEnum;
import logics.grammar.statements.Block_Statement;
import logics.grammar.statements.For_Statement;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.Statement;
import logics.grammar.statements.While_Statement;
import logics.grammar.types.Primitive_Type;
import logics.grammar.types.Primitive_Type.Primitive;

public class Main {

	public static void main(String[] args) {

		Level1 s = new Level1(5, 5, "H", new Coordinate(1, 1), "G", new Coordinate(1, 4));

		Block_Statement b = new Block_Statement();

		//b.addStatemnt(new If_Statement(new Boolean_Literal(true), s.GoRightStatement(), null) );

		//Integer_Binary_Expression opo = new Integer_Binary_Expression(new Integer_Literal(1), new Integer_Literal(1), new Integer_Binary_Operator(BinaryOperatorEnum.plus));
		//Boolean_Binary_Expression e = new Boolean_Binary_Expression(opo, new Integer_Literal(2), new Boolean_Binary_Operator(BinaryOperatorEnum.equals));
		//b.addStatemnt(new If_Statement(e, s.GoRightStatement(), null) );

		//b.addStatemnt(new While_Statement(new Boolean_Literal(false), s.GoRightStatement()));

		//b.addStatemnt(new VariableDeclaration_Statement(new Primitive_Type(Primitive.Int), new Identifier("i"), new Integer_Literal(1)));

		//b.addStatemnt(new VariableDeclaration_Statement(new Primitive_Type(Primitive.Int), new Identifier("i"), new Integer_Literal(1)));
		//Boolean_Binary_Expression e = new Boolean_Binary_Expression(new Identifier("i"), new Integer_Literal(2), new Boolean_Binary_Operator(BinaryOperatorEnum.less));
		//b.addStatemnt(new If_Statement(e, s.GoRightStatement(), null) );

		//b.addStatemnt(new VariableDeclaration_Statement(new Primitive_Type(Primitive.Int), new Identifier("i"), new Integer_Literal(1)));
		//Integer_Unary_Expression e = new Integer_Unary_Expression(new Identifier("i"), new Integer_Unary_Operator(UnaryOperatorEnum.posIncrement));
		//b.addStatemnt(new StatementExpression(e));
		
		/*LinkedList<Expression> vd = new LinkedList<Expression>();
		vd.add(new VariableDeclaration_Expression(new Primitive_Type(Primitive.Int), new Identifier("i"), new Integer_Literal(0)));
		
		Boolean_Binary_Expression cond = new Boolean_Binary_Expression(new Identifier("i"), new Integer_Literal(3), new Boolean_Binary_Operator(BinaryOperatorEnum.less));
		
		LinkedList<Expression> update = new LinkedList<Expression>();
		update.add(new Integer_Unary_Expression(new Identifier("i"), new Integer_Unary_Operator(UnaryOperatorEnum.posIncrement)));
		
		b.addStatemnt(new For_Statement(vd, cond, update, s.GoRightStatement()));*/

		s.RunCode(b);

		//System.out.println(DB.db.get("i"));

	}	
}