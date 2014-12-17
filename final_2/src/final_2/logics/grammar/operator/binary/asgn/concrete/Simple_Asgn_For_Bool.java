package final_2.logics.grammar.operator.binary.asgn.concrete;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.binary.asgn.Bool_Asgn_Op;

public class Simple_Asgn_For_Bool extends Bool_Asgn_Op{

	public Simple_Asgn_For_Bool() {
		super("=");
	}

	@Override
	public void run(Idnt<Boolean> identifier, Boolean operand) {
		identifier.setValue(operand);
	}

}
