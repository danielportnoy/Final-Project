package final_2.logics.grammar.expression.identifier;

import final_2.main.ScopeTable;

public class Int_Idnt extends Idnt<Integer> {

	public Int_Idnt(String name) {
		super(name);
		//setValue(0);	// TODO
	}

	@Override
	public Integer run() {
		return ScopeTable.IntScope.get(getName());
	}

	@Override
	public void setValue(Integer value) {
		if(ScopeTable.IntScope.containsKey(getName()))
			ScopeTable.IntScope.put(getName(),value);
	}

}

/*
package final_2.logics.grammar.expression.identifier;

import final_2.logics.grammar.expression.Int_Expr;
import final_2.main.ScopeTable;

public class Int_Idnt extends Int_Expr {

	private String name;

	public Int_Idnt(String name) {
		this.name = name;
	}

	@Override
	public Integer run() {
		return ScopeTable.IntScope.get(getName());
	}

	public void setValue(Integer value) {
		if(ScopeTable.IntScope.containsKey(getName()))
			ScopeTable.IntScope.put(getName(),value);
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