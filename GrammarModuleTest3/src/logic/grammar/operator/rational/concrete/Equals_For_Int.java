package logic.grammar.operator.rational.concrete;

import logic.grammar.operator.rational.Rtnl_Bin_Op_For_Int;

public class Equals_For_Int extends Rtnl_Bin_Op_For_Int{

	public Equals_For_Int() {
		super("==");
	}

	@Override
	public Boolean run(Integer left, Integer right) {
		return left==right;
	}

}
