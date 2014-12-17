package final_2.logics.grammar.operator.unary.concrete;

import final_2.logics.grammar.operator.unary.Int_Un_Op;

public class Decrement_For_Int extends Int_Un_Op<Integer>{

	public Decrement_For_Int() {
		super("--");
	}

	@Override
	public Integer run(Integer operand) {
		return operand-1;
	}

}
