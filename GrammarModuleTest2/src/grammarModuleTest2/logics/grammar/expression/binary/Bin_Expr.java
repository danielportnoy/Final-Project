package grammarModuleTest2.logics.grammar.expression.binary;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.operator.binary.Bin_Op;

public abstract class Bin_Expr<R , T> extends Expr<R> {

	private Expr<T> left;
	private Bin_Op<R , T> operator;
	private Expr<T> right;
	
	public Bin_Expr(Expr<T> left, Bin_Op<R , T> operator, Expr<T> right) {
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

	public Bin_Op<R , T> getOperator() {
		return operator;
	}

	public void setOperator(Bin_Op<R , T> operator) {
		this.operator = operator;
	}

	public Expr<T> getRight() {
		return right;
	}

	public void setRight(Expr<T> right) {
		this.right = right;
	}
	
	@Override
	public R run() {
		return getOperator().run(getLeft().run(), getRight().run());
	}
	
	@Override
	public String toString() {
		return getLeft().toString() + getOperator().toString() + getRight().toString();
	}
	
}
