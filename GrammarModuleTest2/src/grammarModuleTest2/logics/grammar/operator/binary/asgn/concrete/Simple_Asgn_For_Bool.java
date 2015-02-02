package grammarModuleTest2.logics.grammar.operator.binary.asgn.concrete;

import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.operator.binary.asgn.Bin_Asgn_Op_For_Bool;

public class Simple_Asgn_For_Bool extends Bin_Asgn_Op_For_Bool{

	public Simple_Asgn_For_Bool() {
		super("=");
	}

	@Override
	public void run(Idnt<Boolean> identifier, Boolean operand) {
		identifier.setValue(operand);
	}

}
