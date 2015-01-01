package logic.grammar.operator.assignment.concrete;

import logic.grammar.operator.assignment.Asgn_Bin_Op_For_Int;

public class Simple_Asgn_For_Int extends Asgn_Bin_Op_For_Int {

	public Simple_Asgn_For_Int() {
		super("=");
	}

	@Override
	public Integer run(Integer left, Integer right) {
		return right;
	}

}
