package logic.grammar;

import logic.grammar.expression.arithmetic.Arithmetic_Expr;
import logic.grammar.expression.assignment.Assignment_Expr;
import logic.grammar.expression.logical.Logical_Expr;
import logic.grammar.expression.rational.Rational_Expr;
import logic.grammar.identifier.Bool_Idnt;
import logic.grammar.identifier.Int_Idnt;
import logic.grammar.literal.Bool_Literal;
import logic.grammar.literal.Int_Literal;
import logic.grammar.operator.arithmetic.concrete.Increment;
import logic.grammar.operator.arithmetic.concrete.Plus;
import logic.grammar.operator.assignment.concrete.Simple_Asgn_For_Int;
import logic.grammar.operator.logical.concrete.Logical_And;
import logic.grammar.operator.logical.concrete.Logical_Not;
import logic.grammar.operator.rational.concrete.Equals_For_Bool;
import logic.grammar.operator.rational.concrete.Equals_For_Int;
import logic.grammar.operator.rational.concrete.Greater_Than_For_Int;
import logic.grammar.statement.Block_Stmt;
import logic.grammar.statement.varDec.concrete.Var_Dec_Stmt_Regular;
import logic.grammar.statement.varDec.concrete.Var_Dec_Stmt_Asgn;

public class Test {

	public static void main(String[] args) {
		
		/*
		Int_Idnt i = new Int_Idnt("i");
		Bool_Idnt j = new Bool_Idnt("j");
		
		Var_Dec_Stmt_Asgn Inti = new Var_Dec_Stmt_Asgn(i, new Int_Literal(3));
		Inti.run();
		System.out.println(Inti);
		
		Var_Dec_Stmt_Regular Boolj = new Var_Dec_Stmt_Regular(j);
		Boolj.run();
		System.out.println(Boolj);

		Arithmetic_Expr ipz = new Arithmetic_Expr(i, new Plus() , new Int_Literal(2)); 
		System.out.println(ipz.toString() + " " + ipz.run());
		
		Arithmetic_Expr ipp = new Arithmetic_Expr(i, new Increment()); 
		System.out.println(ipp.toString() + " " + ipp.run());
		
		Rational_Expr iE4 = new Rational_Expr(i, new Equals_For_Int(), new Int_Literal(4));
		System.out.println(iE4.toString() + " " + iE4.run());
		
		Rational_Expr igt1 = new Rational_Expr(i, new Greater_Than_For_Int(), new Int_Literal(1));
		System.out.println(igt1.toString() + " " + igt1.run());
		
		Rational_Expr jEt = new Rational_Expr(j, new Equals_For_Bool() , new Bool_Literal(true));
		System.out.println(jEt.toString() + " " + jEt.run());
		
		Logical_Expr Nj = new Logical_Expr(j, new Logical_Not());
		System.out.println(Nj.toString() + " " + Nj.run());
		
		Logical_Expr iE4_and_Nj = new Logical_Expr(iE4,new Logical_And(),Nj);
		System.out.println(iE4_and_Nj.toString() + " " + iE4_and_Nj.run());
		
		Assignment_Expr iA123 = new Assignment_Expr(i, new Simple_Asgn_For_Int(), new Int_Literal(123));
		System.out.println(iA123.toString());
		iA123.run();
		
		Block_Stmt bs = new Block_Stmt();
		bs.getStatements().add(Inti);
		bs.getStatements().add(Boolj);
		System.out.println(bs);
		*/
	}

}
