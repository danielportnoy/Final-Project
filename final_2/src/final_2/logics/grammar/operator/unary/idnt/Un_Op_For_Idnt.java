package final_2.logics.grammar.operator.unary.idnt;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.unary.Un_Op;

public abstract class Un_Op_For_Idnt<T> extends Un_Op<T, T> {

	public Un_Op_For_Idnt(String name) {
		super(name);
	}
	
	public abstract T run(Idnt<T> identifier);

}
