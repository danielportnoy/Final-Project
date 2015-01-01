package logic.grammar.expression.arithmetic;

import logic.grammar.expression.Int_Expr;
import logic.grammar.identifier.Int_Idnt;
import logic.grammar.operator.arithmetic.Artm_Bin_Op;
import logic.grammar.operator.arithmetic.Artm_Un_Op;

public class Arithmetic_Expr extends Int_Expr{

	private boolean isBinary = false;
	private boolean isUnary = false;

	private Int_Expr left = null;
	private Artm_Bin_Op binOperator = null;
	private Int_Expr right = null;

	private Int_Idnt idetifier = null;
	private Artm_Un_Op unOperator = null;

	public Arithmetic_Expr(Int_Expr left , Artm_Bin_Op binOperator , Int_Expr right) {
		this.isBinary = true;
		this.left = left;
		this.binOperator = binOperator;
		this.right=right;
	}

	public Arithmetic_Expr(Int_Idnt idetifier , Artm_Un_Op unOperator) {
		this.isUnary = true;
		this.idetifier = idetifier;
		this.unOperator = unOperator;
	}

	@Override
	public Integer run() {

		int res = 0;

		if(isBinary)
			res = binOperator.run(left.run(), right.run());
		else if(isUnary){
			res = unOperator.run(idetifier.run());
			idetifier.setValue(res);
		}
		
		return res;
	}

	@Override
	public String toString() {
		if(isBinary)
			return left.toString() + " " + binOperator.toString() + " " + right.toString();
		else
			return idetifier.toString() + unOperator.toString();
	}

}
