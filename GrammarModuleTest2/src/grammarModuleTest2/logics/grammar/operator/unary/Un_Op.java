package grammarModuleTest2.logics.grammar.operator.unary;

import grammarModuleTest2.logics.grammar.operator.Op;

public abstract class Un_Op<R , T> extends Op{
	
	public Un_Op(String name) {
		super(name);
	}
	
	public abstract R run(T operand);
}
