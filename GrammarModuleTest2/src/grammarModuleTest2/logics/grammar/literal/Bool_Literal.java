package grammarModuleTest2.logics.grammar.literal;

import grammarModuleTest2.logics.grammar.expression.Bool_Expr;

public class Bool_Literal extends Bool_Expr {

	private Boolean value;
	
	public Bool_Literal(Boolean value) {
		this.value=value;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public Boolean run() {
		return getValue();
	}

	@Override
	public String toString() {
		return getValue().toString();
	}

}
