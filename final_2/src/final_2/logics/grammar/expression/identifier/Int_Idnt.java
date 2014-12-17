package final_2.logics.grammar.expression.identifier;

import final_2.main.ScopeTable;

public class Int_Idnt extends Idnt<Integer> {

	public Int_Idnt(String name) {
		super(name);
		setValue(0);	// TODO
	}

	@Override
	public Integer run() {
		return ScopeTable.IntScope.get(getName());
	}
	
	@Override
	public void setValue(Integer value) {
		ScopeTable.IntScope.put(getName(),value);
	}

}
