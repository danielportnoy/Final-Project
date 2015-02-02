package grammarModuleTest2.logics.grammar.expression.identifier;

import grammarModuleTest2.main.ScopeTable;

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

/*
package final_2.logics.grammar.expression.identifier;

import final_2.logics.grammar.expression.Bool_Expr;
import final_2.main.ScopeTable;

public class Bool_Idnt extends Bool_Expr {

	private String name;

	public Bool_Idnt(String name) {
		this.name = name;
	}

	public Boolean run() {
		return ScopeTable.BoolScope.get(getName());
	}

	public void setValue(Boolean value) {
		if(ScopeTable.BoolScope.containsKey(getName()))
			ScopeTable.BoolScope.put(getName(),value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}

}
*/