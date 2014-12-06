package main;

import logics.game.Coordinate;
import logics.game.levels.level1.Level1;
import logics.grammar.expression.Binary_Expression.Operator;
import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.While_Statement;

public class Main {

	public static void main(String[] args) {

		Level1 s = new Level1(5, 5, "H", new Coordinate(1, 1), "G", new Coordinate(1, 4));

		Block b = new Block();

		//b.addStatemnt(new If_Statement(new Boolean_Literal(true), s.GoRightStatement(), null) );

		//Integer_Binary_Expression opo = new Integer_Binary_Expression(new Integer_Literal(1), new Integer_Literal(1), Operator.plus);
		//Boolean_Binary_Expression e = new Boolean_Binary_Expression(opo, new Integer_Literal(2), Operator.equals);
		//b.addStatemnt(new If_Statement(e, s.GoRightStatement(), null) );
		
		b.addStatemnt(new While_Statement(new Boolean_Literal(true), s.GoRightStatement()));

		s.RunCode(b);


	}	
}