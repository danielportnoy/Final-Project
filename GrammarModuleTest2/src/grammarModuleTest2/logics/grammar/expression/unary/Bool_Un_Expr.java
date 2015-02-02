package grammarModuleTest2.logics.grammar.expression.unary;

import grammarModuleTest2.logics.grammar.expression.Bool_Expr;
import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.operator.unary.Un_Op;

public abstract class Bool_Un_Expr<T> extends Bool_Expr{

	private Expr<T> operand;
	private Un_Op<Boolean , T> operator;

	public Bool_Un_Expr(Expr<T> operand, Un_Op<Boolean , T> operator) {
		this.operand=operand;
		this.operator=operator;
	}

	public Expr<T> getOperand() {
		return operand;
	}

	public void setOperand(Expr<T> operand) {
		this.operand = operand;
	}

	public Un_Op<Boolean, T> getOperator() {
		return operator;
	}

	public void setOperator(Un_Op<Boolean, T> operator) {
		this.operator = operator;
	}	

	@Override
	public Boolean run() {
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

public abstract class Bool_Un_Expr<T> extends Un_Expr<Boolean, T>{

	public Bool_Un_Expr(Expr<T> operand, Un_Op<Boolean, T> operator) {
		super(operand, operator);
	}

}
*/