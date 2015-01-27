package logic.grammar.operator.assignment.concrete;

import logic.grammar.operator.assignment.Asgn_Bin_Op_For_Bool;

public class Simple_Asgn_For_Bool extends Asgn_Bin_Op_For_Bool {

	public Simple_Asgn_For_Bool() {
		super("=");
	}

	@Override
	public Boolean run(Boolean left, Boolean right) {
		return right;
	}

}
