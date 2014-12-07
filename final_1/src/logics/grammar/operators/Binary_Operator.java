package logics.grammar.operators;

public abstract class Binary_Operator<R> extends Operator {
	
	private BinOperatorEnum operator;
	
	public Binary_Operator(BinOperatorEnum operator) {
		this.operator = operator;
	}
	
	public BinOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(BinOperatorEnum operator) {
		this.operator = operator;
	}
	
	public abstract R operate(R right , R left);
	
}
