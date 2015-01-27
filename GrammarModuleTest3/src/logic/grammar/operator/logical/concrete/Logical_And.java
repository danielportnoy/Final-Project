package logic.grammar.operator.logical.concrete;

import logic.grammar.operator.logical.Lgcl_Bin_Op;

public class Logical_And extends Lgcl_Bin_Op{

	public Logical_And() {
		super("&&");
	}

	@Override
	public Boolean run(Boolean left, Boolean right) {
		return left && right;
	}

}
