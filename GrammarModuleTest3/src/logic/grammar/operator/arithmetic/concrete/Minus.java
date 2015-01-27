package logic.grammar.operator.arithmetic.concrete;

import logic.grammar.operator.arithmetic.Artm_Bin_Op;

public class Minus extends Artm_Bin_Op{

	public Minus() {
		super("-");
	}

	@Override
	public Integer run(Integer left, Integer right) {
		return left-right;
	}

}
