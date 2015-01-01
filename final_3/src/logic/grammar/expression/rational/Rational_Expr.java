package logic.grammar.expression.rational;

import logic.grammar.expression.Bool_Expr;
import logic.grammar.expression.Int_Expr;
import logic.grammar.operator.rational.Rtnl_Bin_Op_For_Bool;
import logic.grammar.operator.rational.Rtnl_Bin_Op_For_Int;

public class Rational_Expr extends Bool_Expr{

	private boolean isForInt = false;
	private boolean isForBool = false;

	private Int_Expr leftInt = null;
	private Rtnl_Bin_Op_For_Int opForInt = null;
	private Int_Expr rightInt = null;

	private Bool_Expr leftBool = null;
	private Rtnl_Bin_Op_For_Bool opForBool = null;
	private Bool_Expr rightBool = null;


	public Rational_Expr(Int_Expr left , Rtnl_Bin_Op_For_Int opForInt , Int_Expr right) {
		this.isForInt = true;
		this.leftInt = left;
		this.opForInt = opForInt;
		this.rightInt = right;
	}

	public Rational_Expr(Bool_Expr left , Rtnl_Bin_Op_For_Bool opForBool , Bool_Expr right) {
		this.isForBool = true;
		this.leftBool = left;
		this.opForBool = opForBool;
		this.rightBool = right;
	}

	@Override
	public Boolean run() {

		boolean res = false;

		if(isForInt)
			res = opForInt.run(leftInt.run(), rightInt.run());
		else if(isForBool)
			res = opForBool.run(leftBool.run(), rightBool.run());

		return res;
	}

	@Override
	public String toString() {
		if(isForInt)
			return leftInt.toString() + " " + opForInt.toString() + " " + rightInt.toString();
		else
			return leftBool.toString() + " " + opForBool.toString() + " " + rightBool.toString();
	}

}
