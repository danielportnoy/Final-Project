package final_2.main;

import final_2.logics.grammar.expression.binary.asgn.concrete.Simple_Asgn_Expr_For_Int;
import final_2.logics.grammar.expression.binary.varDec.concrete.Var_Dec_For_Int;
import final_2.logics.grammar.expression.identifier.Int_Idnt;
import final_2.logics.grammar.literal.Int_Literal;
import final_2.logics.grammar.operator.binary.asgn.concrete.Simple_Asgn_For_Int;

/* TEST 1
import final_2.logics.grammar.expression.binary.concrete.Bool_Bin_Expr_For_Int;
import final_2.logics.grammar.expression.binary.concrete.Int_Bin_Expr_For_Int;
import final_2.logics.grammar.literal.Bool_Literal;
import final_2.logics.grammar.literal.Int_Literal;
import final_2.logics.grammar.operator.concrete.Equals_For_Int;
import final_2.logics.grammar.operator.concrete.Plus_For_Int;
*/

public class Main {

	public static void main(String[] args){

		/* TEST 1
		Int_Literal ilOne = new Int_Literal(1);
		Int_Literal ilTwo = new Int_Literal(2);
		Bool_Literal bl = new Bool_Literal(true);

		Int_Bin_Expr_For_Int oPo = new Int_Bin_Expr_For_Int(ilOne, new Plus_For_Int(), ilOne);

		Bool_Bin_Expr_For_Int oPoEt = new Bool_Bin_Expr_For_Int(oPo, new Equals_For_Int(), ilTwo);

		System.out.println(ilOne.run());
		System.out.println(ilTwo.run());

		System.out.println(bl.run());

		System.out.println(oPoEt.run());
		 */
		
		Int_Literal ilOne = new Int_Literal(1);
		
		Int_Idnt i = new Int_Idnt("i");
		
		Simple_Asgn_Expr_For_Int a = new Simple_Asgn_Expr_For_Int(i, new Simple_Asgn_For_Int(), ilOne);
		
		Var_Dec_For_Int b = new Var_Dec_For_Int(a);
		
		b.run();
		System.out.println(b);
		
	}
}
