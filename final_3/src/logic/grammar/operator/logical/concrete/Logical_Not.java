package logic.grammar.operator.logical.concrete;

import logic.grammar.operator.logical.Lgcl_Un_Op;

public class Logical_Not extends Lgcl_Un_Op{

	public Logical_Not() {
		super("!");
	}

	@Override
	public Boolean run(Boolean operand) {
		return !operand;
	}

}
