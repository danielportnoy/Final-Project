package logic.grammar.identifier;

import logic.grammar.ScopeTable;
import logic.grammar.expression.Bool_Expr;

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