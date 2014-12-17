package final_2.logics.grammar.operator.binary.asgn.concrete;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Int_Asgn_Op;

public class Simple_Asgn_For_Int extends Int_Asgn_Op{

	public Simple_Asgn_For_Int() {
		super("=");
	}

	@Override
	public void run(Idnt<Integer> identifier, Integer operand) {
		identifier.setValue(operand);
	}

}
