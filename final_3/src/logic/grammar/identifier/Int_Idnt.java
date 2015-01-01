package logic.grammar.identifier;

import logic.grammar.ScopeTable;
import logic.grammar.expression.Int_Expr;

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