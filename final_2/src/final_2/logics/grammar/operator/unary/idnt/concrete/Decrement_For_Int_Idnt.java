package final_2.logics.grammar.operator.unary.idnt.concrete;

import final_2.logics.grammar.expression.identifier.Idnt;
import final_2.logics.grammar.operator.unary.idnt.Un_Op_For_Int_Idnt;
import final_2.main.ScopeTable;

public class Decrement_For_Int_Idnt extends Un_Op_For_Int_Idnt{

	public Decrement_For_Int_Idnt() {
		super("--");
	}

	@Override
	public Integer run(Idnt<Integer> identifier) {
		int res = identifier.run() - 1;

		// TODO
		if(ScopeTable.IntScope.containsKey(identifier.getName()))
			ScopeTable.IntScope.put(identifier.getName(), res );		

		return res;
	}

	@Override
	public Integer run(Integer operand) {
		return operand - 1;
	}


}
