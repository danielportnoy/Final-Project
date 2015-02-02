package grammarModuleTest2.logics.grammar.expression.unary;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.Int_Expr;
import grammarModuleTest2.logics.grammar.operator.unary.Un_Op;

public abstract class Int_Un_Expr<T> extends Int_Expr {

	private Expr<T> operand;
	private Un_Op<Integer , T> operator;

	public Int_Un_Expr(Expr<T> operand, Un_Op<Integer , T> operator) {
		this.operand=operand;
		this.operator=operator;
	}

	public Expr<T> getOperand() {
		return operand;
	}

	public void setOperand(Expr<T> operand) {
		this.operand = operand;
	}

	public Un_Op<Integer, T> getOperator() {
		return operator;
	}

	public void setOperator(Un_Op<Integer, T> operator) {
		this.operator = operator;
	}	

	@Override
	public Integer run() {
		return getOperator().run(getOperand().run());
	}

	@Override
	public String toString() {
		return getOperand().toString() + getOperator().toString();
	}
}


/*
package final_2.logics.grammar.expression.unary;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.operator.unary.Un_Op;

public abstract class Int_Un_Expr<T> extends Un_Expr<Integer, T> {

	public Int_Un_Expr(Expr<T> operand, Un_Op<Integer, T> operator) {
		super(operand, operator);
	}

}
 */