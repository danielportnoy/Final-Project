package grammarModuleTest2.logics.grammar.expression.unary;

import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.operator.unary.idnt.Un_Op_For_Idnt;

public abstract class Un_Expr_For_Idnt<T> extends Un_Expr<T, T>{
	
	private Idnt<T> identifier;
	private Un_Op_For_Idnt<T> operator;

	public Un_Expr_For_Idnt(Idnt<T> identifier, Un_Op_For_Idnt<T> operator) {
		super(identifier, operator);
		this.identifier = identifier;
		this.operator = operator;
	}
	
	public Idnt<T> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Idnt<T> identifier) {
		this.identifier = identifier;
	}
	
	public Un_Op_For_Idnt<T> getOperator() {
		return operator;
	}

	public void setOperator(Un_Op_For_Idnt<T> operator) {
		this.operator = operator;
	}

	@Override
	public T run() {
		return getOperator().run(getIdentifier());
	}

}
