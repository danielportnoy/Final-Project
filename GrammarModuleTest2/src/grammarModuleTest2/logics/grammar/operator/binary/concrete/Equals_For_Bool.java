package grammarModuleTest2.logics.grammar.operator.binary.concrete;

import grammarModuleTest2.logics.grammar.operator.binary.Bool_Bin_Op;

public class Equals_For_Bool extends Bool_Bin_Op<Boolean> {

	public Equals_For_Bool() {
		super("==");
	}

	@Override
	public Boolean run(Boolean left, Boolean right) {
		return left==right;
	}

}
