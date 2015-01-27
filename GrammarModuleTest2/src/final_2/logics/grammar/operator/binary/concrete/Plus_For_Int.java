package final_2.logics.grammar.operator.binary.concrete;

import final_2.logics.grammar.operator.binary.Int_Bin_Op;

public class Plus_For_Int extends Int_Bin_Op<Integer> {

	public Plus_For_Int() {
		super("+");
	}

	@Override
	public Integer run(Integer left, Integer right) {
		return left+right;
	}

}
