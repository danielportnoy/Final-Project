package final_2.logics.grammar.operator.binary.asgn;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.Op;

public abstract class Bin_Asgn_Op<T> extends Op {

	public Bin_Asgn_Op(String name) {
		super(name);
	}
	
	public abstract void run(Idnt<T> identifier , T operand);

}
