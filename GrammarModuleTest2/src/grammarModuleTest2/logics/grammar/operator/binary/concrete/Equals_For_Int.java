package grammarModuleTest2.logics.grammar.operator.binary.concrete;

import grammarModuleTest2.logics.grammar.operator.binary.Bool_Bin_Op;

public class Equals_For_Int extends Bool_Bin_Op<Integer>{

	public Equals_For_Int() {
		super("==");
	}

	@Override
	public Boolean run(Integer left, Integer right) {
		return left==right;
	}

}
