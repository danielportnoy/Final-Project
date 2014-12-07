package main;

import logics.game.Coordinate;
import logics.game.levels.level1.Level1;
import logics.grammar.Identifier;
import logics.grammar.expression.Boolean_Binary_Expression;
import logics.grammar.expression.Integer_Binary_Expression;
import logics.grammar.expression.VariableDeclaration_Statement;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.literals.Integer_Literal;
import logics.grammar.operators.Boolean_Binary_Operator;
import logics.grammar.operators.Integer_Binary_Operator;
import logics.grammar.operators.Operator.BinOperatorEnum;
import logics.grammar.statements.Block_Statement;
import logics.grammar.statements.If_Statement;
import logics.grammar.statements.While_Statement;
import logics.grammar.types.Primitive_Type;
import logics.grammar.types.Primitive_Type.Primitive;

public class Main {

	public static void main(String[] args) {

		Level1 s = new Level1(3, 3, "H", new Coordinate(1, 1), "G", new Coordinate(1, 2));

		Block_Statement b = new Block_Statement();

		//b.addStatemnt(new If_Statement(new Boolean_Literal(true), s.GoRightStatement(), null) );

		//Integer_Binary_Expression opo = new Integer_Binary_Expression(new Integer_Literal(1), new Integer_Literal(1), new Integer_Binary_Operator(BinOperatorEnum.plus));
		//Boolean_Binary_Expression e = new Boolean_Binary_Expression(opo, new Integer_Literal(2), new Boolean_Binary_Operator(BinOperatorEnum.equals));
		//b.addStatemnt(new If_Statement(e, s.GoRightStatement(), null) );
		
		//b.addStatemnt(new While_Statement(new Boolean_Literal(false), s.GoRightStatement()));

		//b.addStatemnt(new VariableDeclaration_Statement(new Primitive_Type(Primitive.Int), new Identifier("i"), new Integer_Literal(1)));
		
		//b.addStatemnt(new VariableDeclaration_Statement(new Primitive_Type(Primitive.Int), new Identifier("i"), new Integer_Literal(1)));
		//Boolean_Binary_Expression e = new Boolean_Binary_Expression(new Identifier("i"), new Integer_Literal(2), new Boolean_Binary_Operator(BinOperatorEnum.less));
		//b.addStatemnt(new If_Statement(e, s.GoRightStatement(), null) );
		
		s.RunCode(b);

		//System.out.println(DB.db.get("i"));

	}	
}