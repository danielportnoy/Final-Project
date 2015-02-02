package grammarModuleTest2.logics.grammar.expression.binary;

import grammarModuleTest2.logics.grammar.expression.Bool_Expr;
import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.operator.binary.Bin_Op;

public abstract class Bool_Bin_Expr<T> extends Bool_Expr{

	private Expr<T> left;
	private Bin_Op<Boolean , T> operator;
	private Expr<T> right;

	public Bool_Bin_Expr(Expr<T> left, Bin_Op<Boolean , T> operator, Expr<T> right) {
		this.left=left;
		this.operator=operator;
		this.right=right;
	}

	public Expr<T> getLeft() {
		return left;
	}

	public void setLeft(Expr<T> left) {
		this.left = left;
	}

	public Bin_Op<Boolean , T> getOperator() {
		return operator;
	}

	public void setOperator(Bin_Op<Boolean , T> operator) {
		this.operator = operator;
	}

	public Expr<T> getRight() {
		return right;
	}

	public void setRight(Expr<T> right) {
		this.right = right;
	}

	@Override
	public Boolean run() {
		return getOperator().run(getLeft().run(), getRight().run());
	}

	@Override
	public String toString() {
		return getLeft().toString() + getOperator().toString() + getRight().toString();
	}
}


/*package final_2.logics.grammar.expression.binary;

import final_2.logics.grammar.expression.Expr;
import final_2.logics.grammar.operator.binary.Bin_Op;

public abstract class Bool_Bin_Expr<T> extends Bin_Expr<Boolean, T>{

	public Bool_Bin_Expr(Expr<T> left, Bin_Op<Boolean, T> operator,Expr<T> right) {
		super(left, operator, right);
	}

}
*/