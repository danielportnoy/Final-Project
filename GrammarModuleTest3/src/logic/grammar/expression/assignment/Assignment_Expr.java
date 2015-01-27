package logic.grammar.expression.assignment;

import logic.grammar.expression.Bool_Expr;
import logic.grammar.expression.Int_Expr;
import logic.grammar.expression.Void_Expr;
import logic.grammar.identifier.Bool_Idnt;
import logic.grammar.identifier.Int_Idnt;
import logic.grammar.operator.assignment.Asgn_Bin_Op_For_Bool;
import logic.grammar.operator.assignment.Asgn_Bin_Op_For_Int;

public class Assignment_Expr extends Void_Expr{

	private boolean isForInt = false;
	private boolean isForBool = false;

	private Int_Idnt identifierInt = null;
	private Asgn_Bin_Op_For_Int opForInt = null;
	private Int_Expr rightInt = null;

	private Bool_Idnt identifierBool = null;
	private Asgn_Bin_Op_For_Bool opForBool = null;
	private Bool_Expr rightBool = null;

	public Assignment_Expr(Int_Idnt identifierInt , Asgn_Bin_Op_For_Int opForInt , Int_Expr right) {
		this.isForInt = true;
		this.identifierInt = identifierInt;
		this.opForInt = opForInt;
		this.rightInt = right;
	}

	public Assignment_Expr(Bool_Idnt identifierBool , Asgn_Bin_Op_For_Bool opForBool , Bool_Expr right) {
		this.isForBool = true;
		this.identifierBool = identifierBool;
		this.opForBool = opForBool;
		this.rightBool = right;
	}

	@Override
	public Void run() {

		int resInt = 0;
		boolean resBool = false;

		if(isForInt){
			resInt = opForInt.run(identifierInt.run(), rightInt.run());
			identifierInt.setValue(resInt);
		}
		else if(isForBool){
			resBool = opForBool.run(identifierBool.run(), rightBool.run());
			identifierBool.setValue(resBool);
		}

		return null;
	}

	@Override
	public String toString() {
		if(isForInt)
			return identifierInt.toString() + " " + opForInt.toString() + " " + rightInt.toString();
		else
			return identifierBool.toString() + " " + opForBool.toString() + " " + rightBool.toString();
	}

}
