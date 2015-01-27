package final_2.logics.grammar.operator.binary;

import final_2.logics.grammar.operator.Op;

public abstract class Bin_Op<R , T> extends Op{

	public Bin_Op(String name) {
		super(name);
	}
	
	public abstract R run(T left , T right);
}
