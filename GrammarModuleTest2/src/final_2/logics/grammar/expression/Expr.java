package final_2.logics.grammar.expression;

public abstract class Expr<R> {
	
	public abstract R run();
	
	@Override
	public abstract String toString();
}
