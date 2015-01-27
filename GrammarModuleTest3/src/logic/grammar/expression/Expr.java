package logic.grammar.expression;

public abstract class Expr<R> {
	
	public abstract R run();
	
	@Override
	public abstract String toString();
	
	public boolean equals(Expr<R> other) {	// TODO
		return this.run().equals(other.run());
	}
}
