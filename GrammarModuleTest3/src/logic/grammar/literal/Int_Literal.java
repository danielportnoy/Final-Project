package logic.grammar.literal;

import logic.grammar.expression.Int_Expr;

public class Int_Literal extends Int_Expr {

	private Integer value;

	public Int_Literal(Integer value) {
		this.value=value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public Integer run() {
		return getValue();
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
}
