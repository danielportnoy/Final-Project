package final_2.logics.grammar.operator.unary.concrete;

import final_2.logics.grammar.operator.unary.Bool_Un_Op;

public class Logical_Not_For_Bool extends Bool_Un_Op<Boolean> {

	public Logical_Not_For_Bool() {
		super("!");
	}

	@Override
	public Boolean run(Boolean operand) {
		return !operand;
	}

}
