package final_2.logics.grammar.expression.identifier;

import final_2.main.ScopeTable;

public class Bool_Idnt extends Idnt<Boolean> {

	public Bool_Idnt(String name) {
		super(name);
		//setValue(false);	// TODO
	}

	@Override
	public Boolean run() {
		return ScopeTable.BoolScope.get(getName());
	}
	
	@Override
	public void setValue(Boolean value) {
		if(ScopeTable.BoolScope.containsKey(getName()))
			ScopeTable.BoolScope.put(getName(), value);
	}

}
