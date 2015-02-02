package grammarModuleTest2.logics.grammar.operator.binary;

import grammarModuleTest2.logics.grammar.operator.Op;

public abstract class Bin_Op<R , T> extends Op{

	public Bin_Op(String name) {
		super(name);
	}
	
	public abstract R run(T left , T right);
}
