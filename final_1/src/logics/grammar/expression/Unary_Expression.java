package logics.grammar.expression;

import logics.grammar.operators.Operator;

public abstract class Unary_Expression extends Expression{
	
	private Expression exp;

    private Operator op;
    
    public Unary_Expression(Expression exp , Operator op) {
        this.exp = exp;
        this.op = op;
	}

	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}
    
	
}
