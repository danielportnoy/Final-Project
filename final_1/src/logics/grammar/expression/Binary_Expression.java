package logics.grammar.expression;

import logics.visitor.CodeVisitor;

public class Binary_Expression extends Expression{
	
	public static enum Operator {
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

	private Expression left;

    private Expression right;

    private Operator op;
    
    public Binary_Expression(Expression left, Expression right, Operator op) {
        this.left = left;
        this.right = right;
        this.op = op;
	}
	
	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}

	@Override
	public Expression accept(CodeVisitor v) {
		return v.visit(this);
	}

}
