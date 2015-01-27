package logic.grammar.operator.rational.concrete;

import logic.grammar.operator.rational.Rtnl_Bin_Op_For_Int;

public class Less_Than_For_Int extends Rtnl_Bin_Op_For_Int {

	public Less_Than_For_Int() {
		super("<");
	}

	@Override
	public Boolean run(Integer left, Integer right) {
		return left < right;
	}

}
