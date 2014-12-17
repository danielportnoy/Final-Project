package final_2.logics.grammar.expression.unary;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.operator.unary.Un_Op;

public abstract class Un_Expr<R,T> extends Expr<R> {

	private Expr<T> operand;
	private Un_Op<R , T> operator;
	
	public Un_Expr(Expr<T> operand, Un_Op<R , T> operator) {
		this.operand=operand;
		this.operator=operator;
	}

	public Expr<T> getOperand() {
		return operand;
	}

	public void setOperand(Expr<T> operand) {
		this.operand = operand;
	}

	public Un_Op<R, T> getOperator() {
		return operator;
	}

	public void setOperator(Un_Op<R, T> operator) {
		this.operator = operator;
	}	
	
	@Override
	public R run() {
		return getOperator().run(getOperand().run());
	}
	
	@Override
	public String toString() {
		return getOperand().toString() + getOperator().toString();
	}
}
