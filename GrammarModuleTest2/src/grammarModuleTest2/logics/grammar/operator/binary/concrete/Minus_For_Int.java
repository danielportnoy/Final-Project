package grammarModuleTest2.logics.grammar.operator.binary.concrete;

import grammarModuleTest2.logics.grammar.operator.binary.Int_Bin_Op;

public class Minus_For_Int extends Int_Bin_Op<Integer>{

	public Minus_For_Int() {
		super("-");
	}

	@Override
	public Integer run(Integer left, Integer right) {
		return left-right;
	}

}
