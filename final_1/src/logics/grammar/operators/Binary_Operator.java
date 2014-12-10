package logics.grammar.operators;

public abstract class Binary_Operator<R> extends Operator {
	
	public static enum BinaryOperatorEnum {
        or, 			// ||
        and, 			// &&
        binOr, 			// |
        binAnd, 		// &
        xor, 			// ^
        equals,			// ==
        notEquals, 		// !=
        less, 			// <
        greater, 		// >
        lessEquals, 	// <=
        greaterEquals, 	// >=
        lShift, 		// <<
        rSignedShift, 	// >>
        rUnsignedShift, // >>>
        plus, 			// +
        minus, 			// -
        times, 			// *
        divide, 		// /
        remainder, 		// %
    }	
	
	private BinaryOperatorEnum operator;
	
	public Binary_Operator(BinaryOperatorEnum operator) {
		this.operator = operator;
	}
	
	public BinaryOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(BinaryOperatorEnum operator) {
		this.operator = operator;
	}
	
	public abstract R operate(R right , R left);
	
}
