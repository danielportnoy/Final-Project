package grammarModuleTest2.logics.grammar.expression.identifier;

import grammarModuleTest2.logics.grammar.expression.Expr;

public abstract class Idnt<T> extends Expr<T> {
	
	private String name;
	
	public Idnt(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	public abstract void setValue(T value);

}
