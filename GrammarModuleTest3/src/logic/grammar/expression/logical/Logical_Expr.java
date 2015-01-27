package logic.grammar.expression.logical;

import logic.grammar.expression.Bool_Expr;
import logic.grammar.identifier.Bool_Idnt;
import logic.grammar.operator.logical.Lgcl_Bin_Op;
import logic.grammar.operator.logical.Lgcl_Un_Op;

public class Logical_Expr extends Bool_Expr{

	private boolean isBinary = false;
	private boolean isUnary = false;

	private Bool_Expr left = null;
	private Lgcl_Bin_Op binOperator = null;
	private Bool_Expr right = null;

	private Bool_Idnt idetifier = null;
	private Lgcl_Un_Op unOperator = null;

	public Logical_Expr(Bool_Expr left , Lgcl_Bin_Op binOperator , Bool_Expr right) {
		this.isBinary = true;
		this.left = left;
		this.binOperator = binOperator;
		this.right=right;
	}

	public Logical_Expr(Bool_Idnt idetifier , Lgcl_Un_Op unOperator) {
		this.isUnary = true;
		this.idetifier = idetifier;
		this.unOperator = unOperator;
	}

	@Override
	public Boolean run() {

		boolean res = false;

		if(isBinary)
			res = binOperator.run(left.run(), right.run());
		else if(isUnary){
			res = unOperator.run(idetifier.run());
		}
		
		return res;
	}

	@Override
	public String toString() {
		if(isBinary)
			return left.toString() + " " + binOperator.toString() + " " + right.toString();
		else
			return unOperator.toString() + idetifier.toString();
	}

}
