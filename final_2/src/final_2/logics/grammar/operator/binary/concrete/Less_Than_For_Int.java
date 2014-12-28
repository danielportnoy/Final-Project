package final_2.logics.grammar.operator.binary.concrete;

import final_2.logics.grammar.operator.binary.Bool_Bin_Op;

public class Less_Than_For_Int extends Bool_Bin_Op<Integer> {

	public Less_Than_For_Int() {
		super("<");
	}

	@Override
	public Boolean run(Integer left, Integer right) {
		return left < right;
	}

}
