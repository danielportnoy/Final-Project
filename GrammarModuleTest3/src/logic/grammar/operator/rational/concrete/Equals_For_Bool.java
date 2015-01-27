package logic.grammar.operator.rational.concrete;

import logic.grammar.operator.rational.Rtnl_Bin_Op_For_Bool;

public class Equals_For_Bool extends Rtnl_Bin_Op_For_Bool {

	public Equals_For_Bool() {
		super("==");
	}

	@Override
	public Boolean run(Boolean left, Boolean right) {
		return left==right;
	}

}
