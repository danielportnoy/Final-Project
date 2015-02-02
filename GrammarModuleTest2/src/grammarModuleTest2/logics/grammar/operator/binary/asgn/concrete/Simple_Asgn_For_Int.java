package grammarModuleTest2.logics.grammar.operator.binary.asgn.concrete;

import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.operator.binary.asgn.Bin_Asgn_Op_For_Int;

public class Simple_Asgn_For_Int extends Bin_Asgn_Op_For_Int{

	public Simple_Asgn_For_Int() {
		super("=");
	}

	@Override
	public void run(Idnt<Integer> identifier, Integer operand) {
		identifier.setValue(operand);
	}

}
