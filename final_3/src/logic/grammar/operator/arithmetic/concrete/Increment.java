package logic.grammar.operator.arithmetic.concrete;

import logic.grammar.operator.arithmetic.Artm_Un_Op;

public class Increment extends Artm_Un_Op {

	public Increment() {
		super("++");
	}

	@Override
	public Integer run(Integer operand) {
		return operand + 1;
	}

}
