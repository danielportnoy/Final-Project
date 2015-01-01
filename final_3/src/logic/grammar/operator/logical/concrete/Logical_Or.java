package logic.grammar.operator.logical.concrete;

import logic.grammar.operator.logical.Lgcl_Bin_Op;

public class Logical_Or extends Lgcl_Bin_Op{

	public Logical_Or() {
		super("||");
	}

	@Override
	public Boolean run(Boolean left, Boolean right) {
		return left || right;
	}

}
